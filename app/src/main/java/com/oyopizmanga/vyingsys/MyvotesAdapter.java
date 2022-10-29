package com.oyopizmanga.vyingsys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyvotesAdapter extends FirebaseRecyclerAdapter<Contest, MyvotesAdapter.votesViewholder> {
    public MyvotesAdapter(@NonNull FirebaseRecyclerOptions<Contest> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyvotesAdapter.votesViewholder holder, int position, @NonNull Contest model) {
        holder.fname.setText(model.getFirstname());
        holder.secname.setText(model.getSecondname());
        holder.position.setText(model.getPosition());
        Picasso.get().load(model.getUrl()).into(holder.person);
    }

    @NonNull
    @Override
    public MyvotesAdapter.votesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myvotes, parent, false);
        return new MyvotesAdapter.votesViewholder(view);
    }

    public class votesViewholder extends RecyclerView.ViewHolder {
        TextView fname, secname, position;
        ImageView person;

        public votesViewholder(@NonNull View itemView) {
            super(itemView);
            person = itemView.findViewById(R.id.imguser_get);
            fname = itemView.findViewById(R.id.fname_user_get);
            secname = itemView.findViewById(R.id.sec_name_user);
            position = itemView.findViewById(R.id.user_position);
        }
    }
}
