package com.oyopizmanga.vyingsys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference mbase, mbase1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView hasvoted;
    ConstestAdapter adapter;
    Button president, residents, library, health, hostels, men, sports;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        president = view.findViewById(R.id.president);
        residents = view.findViewById(R.id.residents);
        library = view.findViewById(R.id.library);
        health = view.findViewById(R.id.health);
        hostels = view.findViewById(R.id.hostels);
        men = view.findViewById(R.id.men);
        sports = view.findViewById(R.id.sports);
        // RecyclerView recyclerView = view.findViewById(R.id.recyclerhome);
        mAuth = FirebaseAuth.getInstance();
        president.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), presidentActivity.class));
            }
        });
        residents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), residentsActivity.class));
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), libraryActivity.class));
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), healthActivity.class));
            }
        });
        hostels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), hostelsActivity.class));
            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), menActivity.class));
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), sportActivity.class));
            }
        });
        ceckvoted();
        return view;
    }

    private void ceckvoted() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Voted").child(mAuth.getCurrentUser().getUid());
        reference.orderByChild("contestant").equalTo("voted").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    String uid = FirebaseAuth.getInstance().getUid();
                    // Log.d("TAG", "PARENT: "+ childDataSnapshot.getKey());
                    String name1 = (String) childDataSnapshot.child("url").getValue();
                    if (childDataSnapshot.exists()) {
                        //hasvoted.setText("You have voted");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}