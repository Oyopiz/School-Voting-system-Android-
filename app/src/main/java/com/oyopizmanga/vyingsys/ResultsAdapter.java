package com.oyopizmanga.vyingsys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ResultsAdapter extends FirebaseRecyclerAdapter<Results, ResultsAdapter.resultsViewholder> {
    public ResultsAdapter(@NonNull FirebaseRecyclerOptions<Results> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ResultsAdapter.resultsViewholder holder, int position, @NonNull Results model) {
        holder.vote.setText(model.getVotes()+" "+"Votes");
        holder.name.setText(model.getName());
    }

    @NonNull
    @Override
    public ResultsAdapter.resultsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results, parent, false);
        return new ResultsAdapter.resultsViewholder(view);
    }

    public class resultsViewholder extends RecyclerView.ViewHolder {
        TextView vote, name;

        public resultsViewholder(@NonNull View itemView) {
            super(itemView);
            vote = itemView.findViewById(R.id.mainvote);
            name = itemView.findViewById(R.id.vet);
        }
    }
}