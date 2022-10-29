package com.oyopizmanga.vyingsys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VotesAdapter extends FirebaseRecyclerAdapter<Votes, VotesAdapter.votesViewholder> {
    public VotesAdapter(@NonNull FirebaseRecyclerOptions<Votes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VotesAdapter.votesViewholder holder, int position, @NonNull Votes model) {

    }

    @NonNull
    @Override
    public VotesAdapter.votesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.votesme, parent, false);
        return new VotesAdapter.votesViewholder(view);
    }

    public class votesViewholder extends RecyclerView.ViewHolder {
        TextView vote, name;

        public votesViewholder(@NonNull View itemView) {
            super(itemView);

        }
    }
}