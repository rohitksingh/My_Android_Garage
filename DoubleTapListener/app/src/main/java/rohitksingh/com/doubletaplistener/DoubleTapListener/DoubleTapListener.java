package rohitksingh.com.doubletaplistener.DoubleTapListener;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class DoubleTapListener  implements View.OnClickListener{

    private boolean isRunning= false;
    private int resetInTime =500;
    private int counter=0;

    private DoubleTapCallback listener;

    public DoubleTapListener(Context context)
    {
        listener = (DoubleTapCallback)context;
        Log.d("Double Tap","New");
    }

    @Override
    public void onClick(View v) {


        if(isRunning)
        {
            if(counter==1)
                listener.onDoubleClick(v);
        }

        counter++;

        if(!isRunning)
        {
            isRunning=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(resetInTime);
                        isRunning = false;
                        counter=0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

}
