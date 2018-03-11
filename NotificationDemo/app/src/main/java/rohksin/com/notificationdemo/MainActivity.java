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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getBasicNotification().build());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getAutoCanceableNotification().build());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getNonCancelableNotification().build());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getCustomNotification().build());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getLargeImageNotification().build());
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(getMultilineNotification().build());
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
                .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.ic_launcher))
                .setTicker("This is ticker message")
                .setNumber(4);

    }


    public NotificationCompat.Builder getLargeImageNotification()
    {
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.big_pic))
                .setBigContentTitle("Big title")
                .setSummaryText("This is summary text This is summary text This is summary text This is summary text This is summary text This is summary text This is summary text This is summary text ");

        return getBasicNotification()
                .setStyle(style);
    }

    public NotificationCompat.Builder getMultilineNotification()
    {
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("Big Title")
                .setSummaryText("Summary Text")
                .bigText("Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story Hi this is story ");

        return getBasicNotification()
                .setStyle(style);
    }


    public PendingIntent getContentIntent()
    {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent , 0);
        return  pi;
    }



    public void sendNotification(Notification notification)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, notification);
    }



}
