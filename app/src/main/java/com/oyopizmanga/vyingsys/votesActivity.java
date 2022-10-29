package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.ssw.biometricauthenticationhandler.auth.BiometricAuthenticationHandler;

public class votesActivity extends AppCompatActivity {
    TextView fname, name, url, countme, keybro;
    ImageView imgload;
    FirebaseAuth mAuth;
    DatabaseReference mbase;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button vote;
    TextView hasvoted, positionme;
    VotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);
        mAuth = FirebaseAuth.getInstance();
        keybro = findViewById(R.id.keybro);
        fname = findViewById(R.id.fstname);
        name = findViewById(R.id.sename);
        url = findViewById(R.id.txturl);
        checkvote();
        countme = findViewById(R.id.vote_count);
        rootNode = FirebaseDatabase.getInstance();
        vote = findViewById(R.id.vote);
        imgload = findViewById(R.id.imgload);
        positionme = findViewById(R.id.positionme);
        fname.setText(getIntent().getStringExtra("namesn") + "");
        name.setText(getIntent().getStringExtra("snamesn") + "");
        url.setText(getIntent().getStringExtra("link") + "");
        keybro.setText(getIntent().getStringExtra("keybro") + "");
        positionme.setText(getIntent().getStringExtra("pos") + "");
        String nameme = fname.getText().toString() + " " + name.getText().toString();
        String nameme2 = fname.getText().toString() + name.getText().toString();
        reference = rootNode.getReference("UserVotes");
        countme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("UserVotes").child(getIntent().getStringExtra("pos"));
                reference.child(nameme2).child("votes").setValue(countme.getText().toString());
                reference.child(nameme2).child("Name").setValue(nameme);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Picasso.get().load(url.getText().toString()).into(imgload);
        recyclerView = findViewById(R.id.votesrecycler);
        mbase = FirebaseDatabase.getInstance().getReference("Contestants").child(keybro.getText().toString()).child("Votes");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Contestants").child(keybro.getText().toString()).child("Votes");
                Uservote uservote = new Uservote(url.getText().toString(), fname.getText().toString(), "", keybro.getText().toString());
                reference.child(mAuth.getCurrentUser().getUid()).setValue(uservote);
                Toast.makeText(votesActivity.this, "Congradulations, you have voted", Toast.LENGTH_SHORT).show();
                vote.setEnabled(false);
                vote.setText("You have already voted");
                mypostsupload();
            }
        });
        // Query query = mbase.orderByChild("position").equalTo("Director Hostels(Men)");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Votes> options = new FirebaseRecyclerOptions.Builder<Votes>().setQuery(mbase, Votes.class).build();
        adapter = new VotesAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void checkvote() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Contestants").child(getIntent().getStringExtra("keybro")).child("Votes").child(mAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    setContentView(R.layout.votes);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void mypostsupload() {
        finger();
        vote.setVisibility(View.GONE);
    }

    private void finger() {
        BiometricAuthenticationHandler biometricAuthenticationHandler = new BiometricAuthenticationHandler(votesActivity.this, new BiometricAuthenticationHandler.BiometricAuthenticationHandlerEvents() {
            @Override
            public void onAuthenticationSuccess() {
                setText();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                DatabaseReference reference, reference1;
                String uid = mAuth.getCurrentUser().getUid();
                FirebaseDatabase rootNode;
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Voted");
                reference1 = rootNode.getReference("Results");
                String bro = "voted";
                Voted voted = new Voted(name.getText().toString(), "Voted", url.getText().toString(), fname.getText().toString(), positionme.getText().toString());
                reference.child(uid).push().setValue(voted);
                finish();
                startActivity(new Intent(votesActivity.this, homeActivity.class));
            }

            @Override
            public void onAuthenticationFailed(int reason) {
                switch (reason) {
                    case BiometricAuthenticationHandler.ERROR_NO_HARDWARE:
                        Toast.makeText(votesActivity.this, "No Hardware detected", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_NONE_ENROLLED:
                        Toast.makeText(votesActivity.this, "NNo Finger enrolled", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_HARDWARE_UNAVAILABLE:
                        Toast.makeText(votesActivity.this, "Unavailable Hardware", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_AUTH_FAILED:
                    default:
                        Toast.makeText(votesActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_NO_PERMISSION:
                        Toast.makeText(votesActivity.this, "Permission Not enabled", Toast.LENGTH_SHORT).show();
                        break;
                    case BiometricAuthenticationHandler.ERROR_KEYGUARD_NOT_SECURED:
                        Toast.makeText(votesActivity.this, "Keyboard not secured", Toast.LENGTH_SHORT).show();
                        break;
                }

                //System.out.println("Authentication Failed " + reasonString);
            }

            @Override
            public void onAuthenticationCancelled() {
                Toast.makeText(votesActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
            }
        });

        biometricAuthenticationHandler.startAuthentication("Authenticate", "Use your finger to vote", "Biometric authentication", "CANCEL", R.drawable.ic_fingerprint); //resID - popup dialog image icon resource
    }

    private void setText() {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Contestants").child(keybro.getText().toString()).child("Votes");
        databaseReference.limitToFirst(5);
        Query query = databaseReference.orderByChild("name").equalTo(fname.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                countme.setText(String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}