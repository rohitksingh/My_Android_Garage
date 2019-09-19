package com.asu.firebasetutorial;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = findViewById(R.id.real_time_db);
        db.setOnClickListener(this);

    }

    private void openRDActivity(){
        startActivity(new Intent(this, CloudStoreActivity.class));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.real_time_db:
                openRDActivity();
                break;

            default:
                break;




        }

    }
}


