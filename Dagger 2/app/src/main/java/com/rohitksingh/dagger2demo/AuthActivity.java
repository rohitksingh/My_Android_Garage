package com.rohitksingh.dagger2demo;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.support.DaggerAppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity {


    @Inject
    Drawable catLogo;

    @Inject
    RequestManager requestManager;

    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        logoImageView = findViewById(R.id.logo);
        loadLogo();
    }

    private void loadLogo(){
        requestManager
                .load(catLogo)
                .into(logoImageView);
    }
}