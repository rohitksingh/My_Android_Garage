package rohksin.com.boundservicedemo2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TimerService timerService;
    private Intent timerIntent;
    private boolean timerBound = false;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.timerValue);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(timerService.getTimerValue());
            }
        });

        registerReceiver(new TimerReceiver(),new IntentFilter("TIMER"));
    }


    @Override
    public void onStart()
    {
        super.onStart();
        if(timerIntent==null)
        {
            timerIntent = new Intent(this,TimerService.class);
            bindService(timerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            startService(timerIntent);
        }
    }

    public void onDestroy()
    {
        stopService(timerIntent);
        timerService = null;
        super.onDestroy();
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            TimerService.TimerBinder binder = (TimerService.TimerBinder)service;
            timerService = binder.getService();
            timerBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            timerBound = false;
        }
    };


    public class TimerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("TIMER"))
            {
                textView.setText(intent.getStringExtra("TIMERVALUE"));
            }
        }
    }


}
