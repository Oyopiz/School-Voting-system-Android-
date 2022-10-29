package com.oyopizmanga.vyingsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class results2Acticity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results2_acticity);
    }

    public void residents(View view) {
        String pos = "Director Non-residents";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void library(View view) {
        String pos = "Director Library and academics";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void health(View view) {
        String pos = "Director Health Science and catering";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void hostels(View view) {
        String pos = "Director Hostels(Men)";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void men(View view) {
        String pos = "Director Hostels(Men)";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void sports(View view) {
        String pos = "Director Sports and Recreation";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void president(View view) {
        String pos = "President";
        Intent intent = new Intent(getBaseContext(), resultsActivity.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }
}