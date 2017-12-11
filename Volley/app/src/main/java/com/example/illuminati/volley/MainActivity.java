package com.example.illuminati.volley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView jsonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonText=(TextView)findViewById(R.id.JsonText);

        // Singleton Volley Queue
        requestQueue= Volley.newRequestQueue(this);

        //fetchStringJsonData();                                        <---- Uncomment to load JsonString request
        fetchArrayJsonData();
    }


    //***************************************************************************************************
    // private methods
    //***************************************************************************************************

    private void fetchStringJsonData()
    {
        String url="http://api.androidhive.info/volley/person_array.json";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                jsonText.setText(response);

            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    private void fetchArrayJsonData()
    {
        jsonText.setText("Rohit");
        String url="http://api.androidhive.info/volley/person_array.json";

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        //// Manual Parsing
                        /// Moshi or Gsoncan be used to parse into Model class

                        String allData="";
                        for (int i = 0; i < response.length();i++)
                        {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                jsonText.setText(obj.toString());
                                String name = obj.getString("name");
                                String email = obj.getString("email");
                                JSONObject phone = obj.getJSONObject("phone");
                                String home = phone.getString("home");
                                String mobile = phone.getString("mobile");
                                allData = allData+"\n\n"+name + "\n" + email + "\n" + home + "\n" + mobile;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        jsonText.setText(allData);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);

    }

}
