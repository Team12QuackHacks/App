package com.example.quackhacks2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpecificLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);

        message = "Specific Location";
        // sets title to the proper location of the building
        TextView location = findViewById(R.id.location);
        location.setText(message);
    }

    public void onClick(View v) {

    }
}