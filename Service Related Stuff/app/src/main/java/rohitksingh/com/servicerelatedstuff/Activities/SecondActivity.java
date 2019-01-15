package rohitksingh.com.servicerelatedstuff.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PublicKey;

import rohitksingh.com.servicerelatedstuff.R;
import rohitksingh.com.servicerelatedstuff.Services.TimerIntentService;

public class SecondActivity extends AppCompatActivity {

    /************************************************************************************************************
     *    THIS Activity showcases communication between a service and an Activity using Broadcast Receiver
     *    When to register and unregister BroadcastReceiver to handle Activity state change
     ************************************************************************************************************/


    private TextView textView;
    private Button button;

    private  TimerReceiver receiver;
    private IntentFilter filter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        receiver = new TimerReceiver();
        filter = new IntentFilter("DISPLAY_TIMER");
        registerReceiver(receiver,filter);

        getSupportActionBar().setTitle("Intent Service");

        textView = (TextView)findViewById(R.id.timerText);
        button = (Button)findViewById(R.id.startService);
        button.setText("Start Timer");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, TimerIntentService.class);
                startService(intent);
            }
        });

    }

    /***********************************************************
             It is necessary to unregister Receiver
             to avoid memory leak
     **********************************************************/
    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(receiver);
    }

    /***************************************************************
     *   Register again when the acttivity comes
     *   from paused state again
     *   Orientation change will be handled by onCreate() method
     *
     **************************************************************/
    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(receiver,filter);
    }


    /************************************************************
            RECEIVER TO LISTEN Service broadcast
     ************************************************************/

    class TimerReceiver extends BroadcastReceiver {

        int timerValue;

        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals("DISPLAY_TIMER"))
            {
                timerValue = intent.getIntExtra("TIMER_VALUE",-100);
                textView.setText(timerValue+"");
            }
        }

    }

}
