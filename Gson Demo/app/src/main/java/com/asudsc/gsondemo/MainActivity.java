package com.asudsc.gsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //parsJson1();

        parseJson2();
    }


    //Case 1: When the fields in the json and POJO is same
    public void parsJson1(){

        String jsonResponse = readJsonFromFile(this, "simple.json");
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(jsonResponse, User.class);

        Log.d(TAG, "parseNormalJson: "+user.toSting());

    }

    // Case 2: Mapping of Nested Objects
    public void parseJson2(){

        String jsonResonse = readJsonFromFile(this, "case2.json");
        Gson gson = new GsonBuilder().create();
        UserCase2 user = gson.fromJson(jsonResonse, UserCase2.class);

        Log.d(TAG, "parseJson2: "+user.toString());
    }


    public static String readJsonFromFile(Context context, String fileName){

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}