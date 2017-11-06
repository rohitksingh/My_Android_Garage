package rohksin.com.alarmmanagerdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.SetAlarmManager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setUoAlarmManager();
            }
        });


    }

    public void setUoAlarmManager()
    {
        /*
         2 steps
           1) Create Pending Intent
           2) Register with AlarmManager Service
         */

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("SomeValue","MSG");

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,SystemClock.currentThreadTimeMillis()+(10000), pendingIntent);


    }


}
