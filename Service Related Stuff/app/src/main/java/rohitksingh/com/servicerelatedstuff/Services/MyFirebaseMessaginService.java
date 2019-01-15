package rohitksingh.com.servicerelatedstuff.Services;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import rohitksingh.com.servicerelatedstuff.Activities.FCMActivity;
import rohitksingh.com.servicerelatedstuff.Activities.MainActivity;
import rohitksingh.com.servicerelatedstuff.Activities.SecondActivity;
import rohitksingh.com.servicerelatedstuff.R;

public class MyFirebaseMessaginService extends FirebaseMessagingService {



    /***********************************************************************
     *          FirebaseMessagingService is used when you want to process
     *          Notification data eq. Flash Sale --> Flash sale Activity
     *          This works when the app is in foreground
     *
     *          How will you create Flash sale Activity?
     *          FirebaseMessage + FirebaseMessagingService + FireStore
     *
     ***********************************************************************/
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {

        super.onMessageReceived(remoteMessage);
        showMessage(remoteMessage);



        /*
        Intent intent = new Intent(this,SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        */
    }


    /****************************************************************************************************************
     *
     *              Private Message
     *              This methods is called when the app is in foreground
     *              So when the app is in background The app shows the default notification
     *              else app will show this notification which on click will open FCMActivity
     *
     *              FCMACTIVITY ?
     *              This activity shows a Text in the text which is sent from FirebaseMessaging Console
     *              The data is extra which comes as data payload (KEY-VALUE PAIR)
     *              Here key is "click_action"
     *
     *              getPayloadData ---> put it in IntentExtra ----> getStringExtra in FCMActivity
     *
     *
     *
     **************************************************************************************************************/

    public void showMessage(RemoteMessage remoteMessage)
    {
        Intent intent = new Intent(this, FCMActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("click_action",remoteMessage.getData().get("click_action"));


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("test")
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);

            Log.d("FCM","inside oxygen");
        }
        manager.notify(0, builder.build());
    }




}
