package com.oyopizmanga.vyingsys;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ConstestAdapter extends FirebaseRecyclerAdapter<Contest, ConstestAdapter.contestViewholder> {
    public ConstestAdapter(@NonNull FirebaseRecyclerOptions<Contest> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull contestViewholder holder, int position, @NonNull Contest model) {
        holder.fname.setText(model.getFirstname());
        holder.secname.setText(model.getSecondname());
        holder.position.setText(model.getPosition());
        Picasso.get().load(model.getUrl()).into(holder.img_user);
        String keybro = getRef(position).getKey();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), votesActivity.class);
                String namesn = (String) holder.fname.getText();
                String snamesn = (String) holder.secname.getText();
                String pos = (String) holder.position.getText();
                String link = model.getUrl();
                intent.putExtra("namesn", namesn);
                intent.putExtra("snamesn", snamesn);
                intent.putExtra("link", link);
                intent.putExtra("pos", pos);
                intent.putExtra("keybro", keybro);
                v.getContext().startActivity(intent);
                /*AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Vote for: " + model.getFirstname() + " You will not be able to vote again");
                builder.setTitle("Final Decision");
                builder.setCancelable(false);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        DatabaseReference reference, reference1;

                        String uid = mAuth.getCurrentUser().getUid();
                        FirebaseDatabase rootNode;
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("Voted");
                        reference1 = rootNode.getReference("Results");
                        String bro = "voted";
                        Voted voted = new Voted(model.secondname, "voted", model.url, model.getFirstname(), model.getPosition(), model.getSecondname());
                        reference.child(uid).push().setValue(voted);
                        reference1.push().setValue(voted);
                        Toast.makeText(v.getContext(), "You voted for " + model.getFirstname(), Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(new Intent(v.getContext(), homeActivity.class));
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        */
            }
        });
    }

    @NonNull
    @Override
    public contestViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest, parent, false);
        return new ConstestAdapter.contestViewholder(view);
    }

    public class contestViewholder extends RecyclerView.ViewHolder {
        TextView fname, secname, position;
        ImageView img_user;

        public contestViewholder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname_user);
            secname = itemView.findViewById(R.id.sname_user);
            position = itemView.findViewById(R.id.position_get);
            img_user = itemView.findViewById(R.id.imguser);
        }
    }
}