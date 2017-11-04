package rohksin.com.intentservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Illuminati on 11/2/2017.
 */

public class SecondActivity extends AppCompatActivity {


    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private TextView texrView;
    private Button button;
    public TextView textView;

    public Button startThirdActivityButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        textView = (TextView)findViewById(R.id.counter);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, DemoForeGroundService.class);
                startService(i);
            }
        });

        startThirdActivityButton = (Button)findViewById(R.id.boundServiceActivity);
        startThirdActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });

        receiver = new MyReceiver(textView);
        filter = new IntentFilter();
        filter.addAction("Foreground");
        registerReceiver(receiver,filter);

    }

    @Override
    public void onStop()
    {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        registerReceiver(receiver,filter);
    }



}


class MyReceiver extends BroadcastReceiver{

    private TextView textView;

    public MyReceiver(TextView textView)
    {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context,Intent intent)
    {
        int value = intent.getIntExtra("CountValue",-1);
        textView.setText(value+"");
        Log.d("ForeGround Demo","data receiving");
    }


}