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
import java.util.HashSet;
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
    /***************************************
     {
         "name": "Norman",
         "email": "norman@futurestud.io",
         "age": 26,
         "isDeveloper": true
     }
     ***************************************/
    public void parsJson1(){

        String jsonResponse = readJsonFromFile(this, "case1.json");
        Gson gson = new GsonBuilder().create();
        Case1User user = gson.fromJson(jsonResponse, Case1User.class);

        Log.d(TAG, "parseNormalJson: "+user.toSting());

    }

    // Case 2: Parse Nested Objects
    /***************************************
     {
         "age": 26,
         "email": "norman@futurestud.io",
         "isDeveloper": true,
         "name": "Norman",

         "userAddress": {
             "city": "Magdeburg",
             "country": "Germany",
             "houseNumber": "42A",
             "street": "Main Street"
         }
     }
     ***************************************/
    public void parseJson2(){

        String jsonResonse = readJsonFromFile(this, "case2.json");
        Gson gson = new GsonBuilder().create();
        Case2UserAddress user = gson.fromJson(jsonResonse, Case2UserAddress.class);

        Log.d(TAG, "parseJson2: "+user.toString());
    }


    //Case 3: Nested elements with ArrayList
    /***************************************
     {
         "menu": [
             {
                "description": "Spaghetti",
                "price": 7.99
             },
             {
                "description": "Steak",
                "price": 12.99
             },
             {
                "description": "Salad",
                "price": 5.99
             }
         ],
         "name": "Future Studio Steak House"
     }
     **************************************/
    public void parseJson3(){

        String jsonResponse = readJsonFromFile(this, "case3.json");
        Gson gson = new GsonBuilder().create();
        Case3Restaurant restaurant = gson.fromJson(jsonResponse, Case3Restaurant.class);

        Log.d(TAG, "parseJson3: "+restaurant.toString());

    }


    //Case 4: Parse to Array, ArrayList, Set
    // Same Json but it can be deserialized into Array, List, Set
    // Array is simplest
    // For ArrayList and Set we need to define Type.
    /********************************************

     [
        {
            "description": "Spaghetti",
                "price": 7.99
        },
        {
            "description": "Steak",
                "price": 12.99
        },
        {
            "description": "Salad",
                "price": 5.99
        }
     ]

     ********************************************/
    public void parseJson4(){

        String jsonResponse = readJsonFromFile(this , "case4.json");
        Gson gson = new GsonBuilder().create();

        //Convert to Array
        Case4User[] user = gson.fromJson(jsonResponse, Case4User[].class);

        //Convert to ArrayList. You need to create a Type
        Type userListType = new TypeToken<ArrayList<Case4User>>(){}.getType();
        List<Case4User> _user = gson.fromJson(jsonResponse, userListType);

        //Convert to set
        Type userSetType = new TypeToken<HashSet<Case4User>>(){}.getType();
        HashSet<Case4User> __user = gson.fromJson(jsonResponse, userSetType);

        Log.d(TAG, "parseJson4 array: "+user[0].toString());
        Log.d(TAG, "parseJson4 ArrayList: "+ _user.toString());
        Log.d(TAG, "parseJson4 ArrayList: "+ __user.toString());

    }

    //Case 5: Parse Map Object
    /*********************************************
    {
        "1$": {
            "amount": 1,
            "currency": "Dollar"
        },
        "2$": {
            "amount": 2,
            "currency": "Dollar"
        },
        "3€": {
            "amount": 3,
            "currency": "Euro"
        }
    }
     ********************************************/
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