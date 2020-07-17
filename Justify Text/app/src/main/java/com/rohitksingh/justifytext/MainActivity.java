package com.rohitksingh.justifytext;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.justifyText);
        webView.loadData(getString(R.string.about_boole), "text/html; charset=utf-8", "utf-8");
        webView.setBackgroundColor(0x00000000);

    }

}