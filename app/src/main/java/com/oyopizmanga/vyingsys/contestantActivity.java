package com.oyopizmanga.vyingsys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class contestantActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText fname, secname;
    ImageView img;
    Button done;
    Spinner mainspinner;
    TextView url;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    StorageReference storageReference;
    FirebaseStorage storage;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant);
        mAuth = FirebaseAuth.getInstance();
        done = findViewById(R.id.done);
        mainspinner = findViewById(R.id.mainspiner1);
        String[] position = {"President", "Director Non-residents", "Director Library and academics", "Director Health Science and catering", "Director Hostels(Ladies)", "Director Hostels(Men)", "Director Sports and Recreation"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, position);
        mainspinner.setAdapter(dataAdapter);
        url = findViewById(R.id.url);
        img = findViewById(R.id.img);
        fname = findViewById(R.id.fname);
        secname = findViewById(R.id.secname);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Contestants").child(mAuth.getCurrentUser().getUid());
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img.getDrawable() != null) {
                    if (fname.getText().toString().isEmpty() || secname.getText().toString().isEmpty()) {
                        Toast.makeText(contestantActivity.this, "All fields must be set", Toast.LENGTH_SHORT).show();
                    } else {
                        uploadImage();
                    }
                } else {
                    Toast.makeText(contestantActivity.this, "Contestant Image must be set", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadImage() {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Registering...");
            progressDialog.show();

            // Defining the child of storageReference
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                    firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            Toast.makeText(contestantActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(contestantActivity.this, homeActivity.class));
                            //url.setText(uri.toString());
                            reference.child("firstname").setValue(fname.getText().toString());
                            reference.child("secondname").setValue(secname.getText().toString());
                            reference.child("url").setValue(uri.toString());
                            String spineitem = mainspinner.getSelectedItem().toString();
                            reference.child("position").setValue(spineitem);
                            String uid = mAuth.getCurrentUser().getUid();
                            String emailme = mAuth.getCurrentUser().getEmail();

                        }

                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(contestantActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Progress " + (int) progress + "%");
                }
            });
        }

    }

    public void choose(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK &&
                data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}