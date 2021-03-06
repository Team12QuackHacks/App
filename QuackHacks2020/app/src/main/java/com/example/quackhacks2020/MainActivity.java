package com.example.quackhacks2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private LinearLayout layout;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        database = FirebaseDatabase.getInstance().getReference();
        databaseCatchup();
    }

    private void databaseCatchup() {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    TextView t = new TextView(layout.getContext());
                    t.setText(snapshot.getKey());
                    layout.addView(t);
                    childrenWriter(snapshot, snapshot.getKey());
                }

                TextView text = new TextView(layout.getContext());
                text.setText("Goto Map View");
                layout.addView(text);
                button = new Button(layout.getContext());
                button.setText("Map");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent map = new Intent(v.getContext(), Map.class);
                        startActivity(map);
                    }
                });
                layout.addView(button);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }

            private void childrenWriter(DataSnapshot snapshot, final String parent) {
                for (final DataSnapshot snSh : snapshot.getChildren()) {
                    Button b = new Button(layout.getContext());
                    b.setText(snSh.getKey());
                    b.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(layout.getContext(), SpecificLocationActivity.class);
                            String message = parent + ";" + snSh.getKey();
                            intent.putExtra(EXTRA_TEXT, message);
                            startActivity(intent);
                        }
                    });
                    layout.addView(b);
                }
            }
        });
    }


}
