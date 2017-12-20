package rohksin.com.boundservicedemo2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Illuminati on 12/20/2017.
 */

public class TimerService extends Service {

    private final IBinder timerBinder = new TimerBinder();
    private int timerValue = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return timerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return false;
    }

    @Override
    public void onCreate()
    {
        startTimer();
    }


    public class TimerBinder extends Binder{

        public TimerService getService()
        {
            return TimerService.this;
        }

    }


    public void startTimer()  {


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    timerValue = timerValue+1;
                    Intent intent = new Intent("TIMER");
                    intent.putExtra("TIMERVALUE",timerValue+"");
                    sendBroadcast(intent);
                }
            }
        }).start();


    }


    public String getTimerValue()
    {
        return timerValue+"";
    }

}
