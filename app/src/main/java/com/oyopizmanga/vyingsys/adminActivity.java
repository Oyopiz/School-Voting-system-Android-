package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class adminActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mbase;
    RecyclerView recyclerView;
    adminAdapter adapter;
    TextView election;
    ToggleButton btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        recyclerView = findViewById(R.id.recycleradmin);
        election = findViewById(R.id.election);
        btnstart = findViewById(R.id.togglemain);
        btnstart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status").child("Election");
                    databaseReference.setValue("start");
                    // The toggle is enabled
                } else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status").child("Election");
                    databaseReference.setValue("stop");
                    // The toggle is disabled
                }
            }
        });
        checkstatus();
        mbase = FirebaseDatabase.getInstance().getReference("Users");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = mbase.orderByChild("position").equalTo("Director Hostels(Ladies)");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAnimation(null);
        FirebaseRecyclerOptions<admin> options = new FirebaseRecyclerOptions.Builder<admin>().setQuery(mbase, admin.class).build();
        adapter = new adminAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void checkstatus() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String status = snapshot.child("Election").getValue(String.class);
                if (status.equals("start")) {
                    election.setText("Election Enabled");
                    btnstart.setText("STOP ELECTION");
                } else {
                    election.setText("Election Disabled");
                    btnstart.setText("START ELECTION");
                }
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


    public void stop(View view) {
    }
}