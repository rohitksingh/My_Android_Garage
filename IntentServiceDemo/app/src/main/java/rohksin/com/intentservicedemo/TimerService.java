package rohksin.com.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Illuminati on 11/2/2017.
 */

public class TimerService extends IntentService {



    public TimerService() {
        super("TimerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        int count =30;
        while (count>0)
        {
            count--;
            try {
                Thread.sleep(1000);
                Intent i = new Intent();
                i.setAction("TimerService");
                i.putExtra("TimerValue",count);
                sendBroadcast(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("TimerService",count+"");
        }


    }
}
