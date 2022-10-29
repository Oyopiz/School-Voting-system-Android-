package com.oyopizmanga.vyingsys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ssw.biometricauthenticationhandler.auth.BiometricAuthenticationHandler;

public class hostelsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mbase;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressBar progressBar;
    TextView hasvoted;
    ConstestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostels);
        recyclerView = findViewById(R.id.hostelsrecycler);
        mbase = FirebaseDatabase.getInstance().getReference("Contestants");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = mbase.orderByChild("position").equalTo("Director Hostels(Ladies)");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Contest> options = new FirebaseRecyclerOptions.Builder<Contest>().setQuery(query, Contest.class).build();
        adapter = new ConstestAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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