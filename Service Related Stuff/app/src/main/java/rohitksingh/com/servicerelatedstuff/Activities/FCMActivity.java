package rohitksingh.com.servicerelatedstuff.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rohitksingh.com.servicerelatedstuff.R;

public class FCMActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcm_activity);
        textView = (TextView)findViewById(R.id.msg);

        getSupportActionBar().setTitle("FCM Activity");

        String extraMsg = getIntent().getStringExtra("click_action");
        if(extraMsg!=null) {
            textView.setText(extraMsg);
        }else
        {
            textView.setText("Null value");
        }

    }

}
