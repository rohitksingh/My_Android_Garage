package rohksin.com.networkinglibrarydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Request request;
    private Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///This does not happen in seperate Thread so putting it insise a AsyncTask

        /*
           1) It does not run in seperate thread
           2) Can not make JsonArrayRequest etc
         */

        new NetworkRequest().execute();



    }

    public void makeRequest() throws IOException
    {
        okHttpClient = new OkHttpClient();

        request = new Request.Builder()
                .url("https://api.androidhive.info/volley/person_array.json")
                .build();
        response = okHttpClient.newCall(request).execute();
    }


    private class NetworkRequest extends AsyncTask<Void,Void,Void>{

        @Override
        public Void doInBackground(Void... params)
        {

            try {
                makeRequest();
            }
            catch (IOException e)
            {

            }
            return null;
        }

        @Override
        public void onPostExecute(Void result)
        {
            String s;

            try {
                s =   response.body().string();
                Log.d("res",s);
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }



}


