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
    private MyBroadCastReceiver receiver;
    private TextView textView;
    private IntentFilter filter;

    private Button secondActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.timer);

        filter = new IntentFilter();
        receiver = new MyBroadCastReceiver();
        filter.addAction("TimerService");

        registerReceiver(receiver,filter);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Test","Working");
                Intent intent = new Intent(MainActivity.this,TimerService.class);
                startService(intent);


            }
        });

        secondActivityButton = (Button)findViewById(R.id.secondActivity);
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });


    }


    @Override
    public void onResume()
    {
        super.onResume();
        registerReceiver(receiver,filter);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        unregisterReceiver(receiver);
    }


    class MyBroadCastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Receiver","Nsg received");

            int value = intent.getIntExtra("TimerValue",0);
            textView.setText(value+"");


        }
    }

}



