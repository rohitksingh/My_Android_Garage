package rohksin.com.photohider;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Illuminati on 2/13/2018.
 */

public class ImageDownloadService extends IntentService {

    private int FOREGROUND_ID = 767;

    public ImageDownloadService() {
        super("Download Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Uri uri = intent.getParcelableExtra("IMAGE_URI");
        Log.d("Uri in service", uri.toString());

        startForeground(FOREGROUND_ID,getNotification());
        PhotoUtility.downloadFile(ImageDownloadService.this, uri.toString(),"File");
        stopForeground(true);


    }



    public Notification getNotification()
    {

        Intent intent = new Intent(this, PhotoListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder foregroundNotification = new NotificationCompat.Builder(this);

        foregroundNotification.setOngoing(true);
        foregroundNotification.setContentTitle("DownLoading...")
                .setContentText("downloading")
                .setSmallIcon(android.R.drawable.arrow_down_float)
                .setContentIntent(pendingIntent);

        return foregroundNotification.build();
    }


}
