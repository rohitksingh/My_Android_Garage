package rohksin.com.intentservicedemo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Illuminati on 11/5/2017.
 */

public class StickyService extends IntentService {


    public StickyService()
    {
        super("StickyService");
    }


    @Override
    public int onStartCommand(Intent intent, int flag, int startId)
    {
        super.onStartCommand(intent,flag,startId);
        return START_STICKY;                                   //<---- It makes service run forever
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        int count = 10;

        while (true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count==0)
            {
                break;
            }
            else
            {
                count--;
            }

        }

        Intent intentSticky = new Intent(this,ThirdActivity.class);
        intentSticky.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentSticky);

    }

}
