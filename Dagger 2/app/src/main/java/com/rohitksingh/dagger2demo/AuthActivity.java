package com.rohitksingh.dagger2demo;

import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerAppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.rohitksingh.dagger2demo.viewmodels.AuthViewModel;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity {


    private AuthViewModel authViewModel;

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
        initViewModel();
        loadLogo();
    }

    private void loadLogo(){
        requestManager
                .load(catLogo)
                .into(logoImageView);
    }

    public void initViewModel(){
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }
}