package com.asu.firebasetutorial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    private Button pause, play;
    MediaPlayer song;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        song = MediaPlayer.create(this, R.raw.song);
        pause = findViewById(R.id.play);
        play = findViewById(R.id.pause);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);

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
                playSong();
                break;

            case R.id.pause:
                pauseSong();
                break;

            default:
                break;

        }
    }
}

