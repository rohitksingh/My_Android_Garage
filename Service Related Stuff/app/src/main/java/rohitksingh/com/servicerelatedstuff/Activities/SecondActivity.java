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

import rohitksingh.com.servicerelatedstuff.R;
import rohitksingh.com.servicerelatedstuff.Services.TimerIntentService;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        TimerReceiver receiver = new TimerReceiver();
        IntentFilter filter = new IntentFilter("DISPLAY_TIMER");
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
