package com.rohitksingh.dagger2demo.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    public AuthViewModel(){

        Log.d(TAG, "AuthViewModel: created");

    }
}
