package com.rohksin.firebaserealtimedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Illuminati on 10/21/2017.
 */

public class SecondActivity extends AppCompatActivity {

    TextView textView;

    private Button thirdActivity;

    DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();

    DatabaseReference modelReference = rootReference.child("Mangoose");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        textView = (TextView)findViewById(R.id.modelText);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDB();
            }
        });

        thirdActivity = (Button)findViewById(R.id.thirdActivity);

        thirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, FireBaseStorageActivity.class));
            }
        });


    }

    @Override
    public void onStart()
    {
        super.onStart();
        modelReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Movie movie = dataSnapshot.getValue(Movie.class);
                textView.setText(movie.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void updateDB()
    {
        Movie movie = new Movie();
        movie.setName("Fight Club");
        movie.setImage("PlaceholderImage");
        modelReference.setValue(movie);

    }
}
