package rohitksingh.com.servicerelatedstuff.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rohitksingh.com.servicerelatedstuff.R;
import rohitksingh.com.servicerelatedstuff.Services.TimerService;

public class FirstActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        textView = (TextView)findViewById(R.id.timerText);
        button = (Button)findViewById(R.id.startService);
        button.setText("Start Timer");

        TimerReceiver receiver = new TimerReceiver();
        IntentFilter filter = new IntentFilter("DISPLAY_TIMER");
        registerReceiver(receiver,filter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, TimerService.class);
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
