package com.oyopizmanga.vyingsys;

import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminAdapter extends FirebaseRecyclerAdapter<admin, adminAdapter.adminViewholder> {
    public adminAdapter(@NonNull FirebaseRecyclerOptions<admin> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminAdapter.adminViewholder holder, int position, @NonNull admin model) {
        String keybro = getRef(position).getKey();
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setMessage("Remove User?");
                builder.setTitle("Remove");
                builder.setCancelable(false);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(keybro);
                        databaseReference.removeValue();
                        Toast.makeText(holder.itemView.getContext(), model.getUsername() + " " + "was removed", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        holder.username.setText(model.getUsername());
        holder.role.setText(model.getIdentity());
    }

    @NonNull
    @Override
    public adminAdapter.adminViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users, parent, false);
        return new adminAdapter.adminViewholder(view);
    }

    public class adminViewholder extends RecyclerView.ViewHolder {
        TextView username, role;
        ImageView delete;

        public adminViewholder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.imageDelete);
            username = itemView.findViewById(R.id.nameadmin);
            role = itemView.findViewById(R.id.roleadmin);

        }
    }
}
