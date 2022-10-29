package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class resultsActivity extends AppCompatActivity {
    TextView name, vote;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    DatabaseReference mbase;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView hasvoted;
    ResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mAuth = FirebaseAuth.getInstance();
        String sessionId = getIntent().getStringExtra("pos");
        recyclerView = findViewById(R.id.resultsrecycler);
        mbase = FirebaseDatabase.getInstance().getReference("UserVotes").child(getIntent().getStringExtra("pos"));
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Results> options = new FirebaseRecyclerOptions.Builder<Results>().setQuery(mbase, Results.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new ResultsAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
