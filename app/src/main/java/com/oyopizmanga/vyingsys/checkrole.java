package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class checkrole extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_checkrole);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.orderByChild("uid").equalTo(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    String uid = FirebaseAuth.getInstance().getUid();
                    // Log.d("TAG", "PARENT: "+ childDataSnapshot.getKey());
                    String role = (String) childDataSnapshot.child("identity").getValue();
                    if (role.equals("Contestant")) {
                        startActivity(new Intent(checkrole.this, homeActivity.class));
                    } else if (role.equals("Voter")) {
                        startActivity(new Intent(checkrole.this, homeActivity.class));
                    } else if (role.equals("Admin")) {
                        startActivity(new Intent(checkrole.this, adminActivity.class));
                    } else {
                        Toast.makeText(checkrole.this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Results1:
                startActivity(new Intent(this, results2Acticity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}