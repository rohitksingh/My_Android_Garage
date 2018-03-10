package rohksin.com.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotidication(getBasicNotification().build());
            }
        });

    }




    public NotificationCompat.Builder getBasicNotification()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("Notification Title")
                .setContentText("Notification description random text random text  random text random text random text random text random text random text random text random text random text")
                .setContentIntent(getContentIntent());
        return builder;
    }

    public NotificationCompat.Builder getAutoCanceableNotification()
    {
        return getBasicNotification()
                .setAutoCancel(true);
    }


    public NotificationCompat.Builder getNonCancelableNotification()
    {
        return getBasicNotification()
                .setOngoing(true);
    }


    public NotificationCompat.Builder getCustomNotification()
    {

        return getBasicNotification()
                .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), android.R.drawable.stat_sys_download))
                .setTicker("This is ticker message")
                .setNumber(4);

    }



    public PendingIntent getContentIntent()
    {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent , 0);
        return  pi;
    }



    public void sendNotidication(Notification notification)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, notification);
    }







}
