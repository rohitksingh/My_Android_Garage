package com.rohitksingh.retrofit_coroutine.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rohitksingh.retrofit_coroutine.R
import com.rohitksingh.retrofit_coroutine.networks.RetrofitClient
import com.rohitksingh.retrofit_coroutine.networks.Webservice
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var userDetailTextView : TextView

    var client : Webservice = RetrofitClient().webservice


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}