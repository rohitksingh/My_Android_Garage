package rohksin.com.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private Button secondActivityButton;
    private Button stickyService;

    private MyBroadCastReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filter = new IntentFilter();
        filter.addAction("TimerService");
        receiver = new MyBroadCastReceiver();
        registerReceiver(receiver,filter);                     //<-------- Receiver Registered

        textView = (TextView)findViewById(R.id.timer);
        button = (Button)findViewById(R.id.button);
        secondActivityButton = (Button)findViewById(R.id.secondActivity);
        stickyService = (Button)findViewById(R.id.stickyService);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,TimerService.class);
                startService(intent);

            }
        });

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        stickyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, StickyService.class));
            }
        });

    }


    //**************************************************************************************
    //  Activity callback methods
    //**************************************************************************************

    @Override
    public void onResume()
    {
        super.onResume();
        registerReceiver(receiver,filter);                                      // For state change eq orientation and pause
    }

    @Override
    public void onStop()
    {
        super.onStop();
        unregisterReceiver(receiver);                                          // Prevents Memory leak
    }

    //**************************************************************************************
    //  Private Methods
    //**************************************************************************************

    public void setUpUi()
    {

    }


    //**************************************************************************************
    //  BroadcastRecevier
    //**************************************************************************************

    class MyBroadCastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            int value = intent.getIntExtra("TimerValue",0);
            textView.setText(value+"");
        }
    }

}



