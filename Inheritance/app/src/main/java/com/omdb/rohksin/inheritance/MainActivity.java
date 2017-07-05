package com.omdb.rohksin.inheritance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AnimationActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,OtherActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public int provideParentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public View setParentLayout() {
        View parent= (View)findViewById(R.id.parentPanel);
        return parent;
    }
}
