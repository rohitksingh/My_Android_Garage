package rohitksingh.com.servicerelatedstuff.Services;

import android.app.IntentService;
import android.content.Intent;

public class TimerIntentService extends IntentService {

    private Thread timer;

    public TimerIntentService()
    {
         super("TIMER INTENT SERVICE");
    }

    /**********************************************************
        This is the abstract method which is called by System
     *********************************************************/
    @Override
    public void onHandleIntent(Intent intent)
    {
        configureTimer();
    }

    public void configureTimer() {

        Intent displayTimerIntemt = new Intent("DISPLAY_TIMER");
        for(int i=10;i>=0;i--)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayTimerIntemt.putExtra("TIMER_VALUE",i);
            sendBroadcast(displayTimerIntemt);
        }
    }
}
