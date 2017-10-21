package com.rohksin.firebaserealtimedb;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button sunny;
    private Button foggy;

    DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = rootref.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.weather);
        sunny = (Button)findViewById(R.id.sunny);
        foggy = (Button)findViewById(R.id.foggy);

        sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionRef.setValue("It's Sunny");
            }
        });

        foggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionRef.setValue("It's Foggy !!!");
            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String weather = dataSnapshot.getValue(String.class);
                textView.setText(weather);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Weatther Error: ","Error while reading values");
            }
        });


    }

}