package com.asu.firebasetutorial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener, EventListener<QuerySnapshot> {

    private static final String TAG = "MusicActivity";

    private Button pause, play;
    MediaPlayer song;
    private final String PLAY_SONG = "MusicActivity.PLAY_SONG";
    private final String PAUSE_SONG = "MusicActivity.PAUSE_SONG";


    FirebaseFirestore maindatabase;
    CollectionReference songRepository;
    private ListenerRegistration registration;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        song = MediaPlayer.create(this, R.raw.song);
        pause = findViewById(R.id.play);
        play = findViewById(R.id.pause);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        initFirebaseCloudStorage();

        registration = songRepository.addSnapshotListener(this);

    }

    private void initFirebaseCloudStorage(){

        maindatabase = FirebaseFirestore.getInstance();
        songRepository = maindatabase.collection("Song");
    }

    private void changeStatus(String status){
        Log.d(TAG, "changeStatus: "+status);
        songRepository.document("song")
                .set(new Song(status));
    }

    private void playSong(){

        song.start();
    }

    private void pauseSong(){
        song.pause();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.play:
                //playSong();
                changeStatus(PLAY_SONG);
                break;

            case R.id.pause:
                //pauseSong();
                changeStatus(PAUSE_SONG);
                break;

            default:
                break;

        }
    }


    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

        for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

            if(dc.getType().toString().equals("MODIFIED")) {

                String state = dc.getDocument().getString("state");

                if (state.equals(PLAY_SONG)) {

                    playSong();

                } else if (state.equals(PAUSE_SONG)){

                    pauseSong();

                }
            }
            Log.d(TAG, "onEvent: "+dc.getType().toString()+" "+dc.getDocument().getData());


        }
    }
}

