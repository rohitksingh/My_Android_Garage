package rohitksingh.com.servicerelatedstuff.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TimerService extends Service {

    /*****************************************************************************************************************

     This Service is an example of Started Service
     since it does not have implementation for onBind thus it can not be Bound hence it is plain Started Service
     A Service can be both started and bound at the same time

     This demo showcases
     Lifecycle methods

     onCreate()             - is called by System only once when the service is running
     onStartCommand()       - is called by the System when the Activity calls startService method
     onBind()               - is called by the System  when the Activity calls bindService method
     onDestroy()            - is called by System when the service calls selfstop() or stopService;

     So even if you press button multiple times Service will run only once.

     ******************************************************************************************************************/



    private Thread timer;
    private int startId;

    /*****************************************************************
              onCreate()
              Used for initial setup purpose. it is called only once
     ******************************************************************/
    @Override
    public void onCreate()
    {
        configureTimer();
        Log.d("Callback","onStart");
    }


    /******************************************************************
            If there is no implementation provided it can not be bound.
            If you dont intent to provide binding return null
     ******************************************************************/
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d("Callback","onBind");
         return null;
    }


    /******************************************************************
        It is called by System when startService is called by user
        it will be called by System as many times user calls startService
     ******************************************************************/
    @Override
    public int onStartCommand(Intent intent, int flag, int startId)
    {

           this.startId = startId;

           Log.d("Callback","onStartCommand");

           if(!timer.isAlive()) {
               timer.start();
           }

           return flag;
    }

    /***************************************************************
         called by System when selfStop() or stopService is called

     **************************************************************/
    @Override
    public void onDestroy()
    {
        Log.d("Callback","onDestroy");
    }


    /************************************************************
           Private method
     ***********************************************************/
    private void configureTimer()
    {
         timer = new Thread(new Runnable() {
            @Override
            public void run() {

                Intent displayTimerIntemt = new Intent("DISPLAY_TIMER");

                for(int i=60;i>=0;i--)
                {
                    Log.d("TIMER", i+"");
                    try {
                        Thread.sleep(1000);

                        displayTimerIntemt.putExtra("TIMER_VALUE",i);
                        sendBroadcast(displayTimerIntemt);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                stopSelf();
                //TimerService.this.stopSelf(startId);   //  Both are fine

            }
        });
    }


}
