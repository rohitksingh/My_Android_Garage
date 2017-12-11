package com.example.illuminati.services.Services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.illuminati.services.MainActivity;

/**
 * Created by Illuminati on 4/6/2017.
 */
public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        new SendFile().execute();
    }


    @Override
    public void onDestroy()
    {
        Log.d("Rohit","Service Destroyed");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startid) {

        return 0;
    }


    private class SendFile extends AsyncTask<Void, Integer, Void> {

        Intent intent;

        public void onPreExecute()
        {
            intent = new Intent(MainActivity.UPDATE_UI);
        }

        @Override
        public Void doInBackground(Void... Params)
        {

            for(int i=0;i<100;i++) {

                intent.putExtra(MainActivity.VALUE_I_NEED,i+"");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendBroadcast(intent);
            }
            return null;
        }



        public void onProgressUpdate(Integer... values)
        {

        }

        @Override
        public void onPostExecute(Void params)
        {


        }


    }


}


