package com.rohitksingh.dagger2demo.viewmodels;

import android.util.Log;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

//You can not pass parameter in Constructor. That is why using Constructor Injection
public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    @Inject
    public AuthViewModel(){

        Log.d(TAG, "AuthViewModel: created");

    }
}
