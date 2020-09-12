package com.example.quackhacks2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SpecificLocationActivity extends AppCompatActivity {

    private Spinner dropdown;
    private String[] locations;
    private DatabaseReference database;
    private int curWaitTime;
    private TextView curWait;
    private String broadTerm, specificLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);
        locations = message.split(" ");
        // sets title to the proper location of the building
        TextView location = findViewById(R.id.location);
        broadTerm = locations[0]+ " " + locations[1];


        specificLocation = "";
        for (int i = 2; i < locations.length; i++) {
            if (i != locations.length - 1) {
                specificLocation += locations[i] + " ";
            } else {
                specificLocation += locations[i];
            }
        }

        location.setText(specificLocation);
        //creates instance of firebase database
        database = FirebaseDatabase.getInstance().getReference();


        curWait = findViewById(R.id.curWaitTime);
        DatabaseReference locationData = database.child(broadTerm).child(specificLocation);
        locationData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                curWait.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"0", "10", "20", "30"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    public void onClick(View v) {
        int wait = Integer.parseInt(dropdown.getSelectedItem().toString());
        database.child(broadTerm).child(specificLocation).setValue(wait);
    }
}