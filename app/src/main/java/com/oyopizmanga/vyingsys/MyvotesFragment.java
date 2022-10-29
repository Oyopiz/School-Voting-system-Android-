package com.oyopizmanga.vyingsys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyvotesFragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference mbase;
    RecyclerView recyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView hasvoted;
    MyvotesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myvotes, container, false);
        mAuth = FirebaseAuth.getInstance();
        //hasvoted = view.findViewById(R.id.txtvoted);
        recyclerView = view.findViewById(R.id.recyclermuvotes);
        mbase = FirebaseDatabase.getInstance().getReference("Voted").child(mAuth.getCurrentUser().getUid());
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Contest> options = new FirebaseRecyclerOptions.Builder<Contest>().setQuery(mbase, Contest.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new MyvotesAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
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