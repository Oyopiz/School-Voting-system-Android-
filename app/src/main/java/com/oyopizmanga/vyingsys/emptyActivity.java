package com.oyopizmanga.vyingsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emptyActivity extends AppCompatActivity {
Button contestant,voter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        contestant=findViewById(R.id.btncontestant);
        voter=findViewById(R.id.btnvoter);
        contestant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(emptyActivity.this, contestantActivity.class));
            }
        });
        voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(emptyActivity.this, homeActivity.class));
            }
        });
    }
}