package com.example.quackhacks2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, SpecificLocationActivity.class);
        String message = "Dining Halls Brittain Dining Hall";
        intent.putExtra(EXTRA_TEXT, message);
        startActivity(intent);
    }
}
