package rohksin.com.intentservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Illuminati on 11/4/2017.
 */

public class DemoBoundService extends Service {

    private int currentTimer;
    private IBinder binder= new MyBinder();

    @Override
    public void onCreate()
    {
        super.onCreate();

        /*
            Normal Service runs on UI Thread hence creating a new Thread
         */

        Thread t1 = new Thread(

                new Runnable() {
                    @Override
                    public void run() {

                        while (true)
                        {
                            try {
                                Thread.sleep(1000);
                                currentTimer++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

        );
        t1.start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onRebind(Intent intent)
    {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return true;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


    // Interface to communicate
    public class MyBinder extends Binder{

        DemoBoundService getService()
        {
            return DemoBoundService.this;
        }

    }

    ///// This is the method which will be invoked by client
    public int getCurrentTimerValue()
    {
        return currentTimer;
    }

}
