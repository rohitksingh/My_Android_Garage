package rohksin.com.intentservicedemo;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Illuminati on 11/3/2017.
 */

public class DemoForeGroundService extends IntentService {

    private static int FOREGROUND_ID = 1543;

    public DemoForeGroundService()
    {
        super("DemoForeGroundService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        startForeground(FOREGROUND_ID,getNotification());

        int count = 30;
        while (count>0)
        {
            try {
                Thread.sleep(1000);
                count--;
                Intent resultIntent = new Intent();
                resultIntent.setAction("Foreground");
                resultIntent.putExtra("CountValue",count);
                sendBroadcast(resultIntent);

                Log.d("ForeGround Demo","data sending");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stopForeground(true);

    }


    public Notification getNotification()
    {

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);


        NotificationCompat.Builder foregroundNotification = new NotificationCompat.Builder(this);
        foregroundNotification.setOngoing(true);

        foregroundNotification.setContentTitle("MY Foreground Notification")
                .setContentText("This is the first foreground notification Peace")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentIntent(pendingIntent);


        return foregroundNotification.build();
    }


}
