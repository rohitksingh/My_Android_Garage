package com.rohksin.moshi;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://api.androidhive.info/volley/person_array.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String jsonResponse = response.toString();

                Moshi moshi = new Moshi.Builder().build();

                Type type = Types.newParameterizedType(List.class, Person.class);



                JsonAdapter<List<Person>> adapter = moshi.adapter(type);

                try {
                    List<Person> persons = adapter.fromJson(jsonResponse);
                    Log.d("Size",persons.size()+" size");

                    for(int i=0;i<persons.size();i++)
                    {
                        Log.d("Person",persons.get(i).toString());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(request);


    }


}
