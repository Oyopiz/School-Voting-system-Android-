package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssw.biometricauthenticationhandler.auth.BiometricAuthenticationHandler;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.callback.FingerprintDialogSecureCallback;
import me.aflak.libraries.dialog.DialogAnimation;
import me.aflak.libraries.dialog.FingerprintDialog;

public class registerActivity extends AppCompatActivity {
    EditText email, password, username, regnumber;
    Button btn;
    Spinner spinner;
    FirebaseDatabase rootNode, rootNode1;
    DatabaseReference reference, reference1;
    FirebaseAuth mAuth;
    RadioGroup radioGroup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RadioButton voter, contestant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        btn = findViewById(R.id.reg);
        spinner = findViewById(R.id.mainspiner);
        regnumber = findViewById(R.id.reg_number);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Contestants");
        rootNode1 = FirebaseDatabase.getInstance();
        String[] position = {"Contestant", "Voter", "Admin"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, position);
        spinner.setAdapter(dataAdapter);
        reference1 = rootNode1.getReference("Voters");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameme = username.getText().toString();
                String regme = regnumber.getText().toString();
                String passwordme = password.getText().toString();
                String emailme = email.getText().toString();
                if (usernameme.isEmpty() || passwordme.isEmpty() || emailme.isEmpty() || regme.isEmpty()) {
                    Toast.makeText(registerActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (!emailme.matches(emailPattern)) {
                    Toast.makeText(registerActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                } else {
                    checkprint();
                }
            }
        });
    }

    private void registerme(String emailme, String passwordme) {
        mAuth.createUserWithEmailAndPassword(emailme, passwordme).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String usernamemeb = username.getText().toString();
                    String regme = regnumber.getText().toString();
                    String spineermain = spinner.getSelectedItem().toString();
                    String urlme = "";
                    String position = "";
                    String fname = "";
                    String sname = "";
                    if (spineermain == "Contestant") {
                        Userhelper userhelper = new Userhelper(usernamemeb, regme, urlme, fname, sname, spineermain, position, mAuth.getCurrentUser().getUid());
                        reference.child(mAuth.getCurrentUser().getUid()).setValue(userhelper);
                        startActivity(new Intent(registerActivity.this, contestantActivity.class));
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(userhelper);
                        Toast.makeText(registerActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    } else if (spineermain == "Voter") {
                        Uservoters uservoters = new Uservoters(usernamemeb, regme, "Voter", mAuth.getCurrentUser().getUid());
                        reference1.child(mAuth.getCurrentUser().getUid()).setValue(uservoters);
                        startActivity(new Intent(registerActivity.this, homeActivity.class));
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(uservoters);
                        Toast.makeText(registerActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    } else if (spineermain == "Admin") {
                        Uservoters uservoters = new Uservoters(usernamemeb, regme, "Admin", mAuth.getCurrentUser().getUid());
                        reference1.child(mAuth.getCurrentUser().getUid()).setValue(uservoters);
                        startActivity(new Intent(registerActivity.this, homeActivity.class));
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(uservoters);
                        Toast.makeText(registerActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(registerActivity.this, "Can't get", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(registerActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkprint() {
        BiometricAuthenticationHandler biometricAuthenticationHandler = new BiometricAuthenticationHandler(registerActivity.this, new BiometricAuthenticationHandler.BiometricAuthenticationHandlerEvents() {
            @Override
            public void onAuthenticationSuccess() {
                String usernameme = username.getText().toString();
                String regme = regnumber.getText().toString();
                String passwordme = password.getText().toString();
                String emailme = email.getText().toString();
                registerme(emailme, passwordme);

            }

            @Override
            public void onAuthenticationFailed(int reason) {
                switch (reason) {
                    case BiometricAuthenticationHandler.ERROR_NO_HARDWARE:
                        Toast.makeText(registerActivity.this, "No Hardware detected", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_NONE_ENROLLED:
                        Toast.makeText(registerActivity.this, "NNo Finger enrolled", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_HARDWARE_UNAVAILABLE:
                        Toast.makeText(registerActivity.this, "Unavailable Hardware", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_AUTH_FAILED:
                    default:
                        Toast.makeText(registerActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_NO_PERMISSION:
                        Toast.makeText(registerActivity.this, "Permission Not enabled", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_KEYGUARD_NOT_SECURED:
                        Toast.makeText(registerActivity.this, "Keyboard not secured", Toast.LENGTH_SHORT).show();
                        break;
                }

                //System.out.println("Authentication Failed " + reasonString);
            }

            @Override
            public void onAuthenticationCancelled() {
                Toast.makeText(registerActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
            }
        });

        biometricAuthenticationHandler.startAuthentication("Authenticate", "Use your finger to vote", "Biometric authentication", "CANCEL", R.drawable.ic_fingerprint); //resID - popup dialog image icon resource
    }

    public void login(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}