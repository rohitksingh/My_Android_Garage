package com.asudsc.gsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //parsJson1();
        //parseJson2();
        //parseJson3();
        parseJson4();
    }


    //Case 1: When the fields in the json and POJO is same
    public void parsJson1(){

        String jsonResponse = readJsonFromFile(this, "case1.json");
        Gson gson = new GsonBuilder().create();
        Case1User user = gson.fromJson(jsonResponse, Case1User.class);

        Log.d(TAG, "parseNormalJson: "+user.toSting());

    }

    // Case 2: Mapping of Nested Objects
    public void parseJson2(){

        String jsonResonse = readJsonFromFile(this, "case2.json");
        Gson gson = new GsonBuilder().create();
        Case2UserAddress user = gson.fromJson(jsonResonse, Case2UserAddress.class);

        Log.d(TAG, "parseJson2: "+user.toString());
    }


    //Case 3: Nested elements with ArrayList
    public void parseJson3(){

        String jsonResponse = readJsonFromFile(this, "case3.json");
        Gson gson = new GsonBuilder().create();
        Case3Restaurant restaurant = gson.fromJson(jsonResponse, Case3Restaurant.class);

        Log.d(TAG, "parseJson3: "+restaurant.toString());

    }


    //Case 4: Parse a List
    public void parseJson4(){

        String jsonResponse = readJsonFromFile(this , "case4.json");
        Gson gson = new GsonBuilder().create();

        //Convert to Array
        Case4User[] user = gson.fromJson(jsonResponse, Case4User[].class);

        //For ArrayList. You need to create a Type
        Type userListType = new TypeToken<ArrayList<Case4User>>(){}.getType();
        List<Case4User> _user = gson.fromJson(jsonResponse, userListType);

        Log.d(TAG, "parseJson4 array: "+user[0].toString());
        Log.d(TAG, "parseJson4 ArrayList: "+ _user.toString());

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