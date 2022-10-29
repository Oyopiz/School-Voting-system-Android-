package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button btn;
    FirebaseAuth mAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.emaillogin);
        password = findViewById(R.id.passwordlogin);
        btn = findViewById(R.id.login);
        login();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordme = password.getText().toString();
                String emailme = email.getText().toString();
                if (passwordme.isEmpty() || emailme.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (!emailme.matches(emailPattern)) {
                    Toast.makeText(MainActivity.this, "Please Use a valid email", Toast.LENGTH_SHORT).show();
                } else {
                    loginme(emailme, passwordme);
                }
            }
        });
    }

    private void login() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "No user detected", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "You are already logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, checkrole.class));
        }
    }

    private void loginme(String emailme, String passwordme) {
        mAuth.signInWithEmailAndPassword(emailme, passwordme).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, checkrole.class));
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(this, registerActivity.class));
    }
}