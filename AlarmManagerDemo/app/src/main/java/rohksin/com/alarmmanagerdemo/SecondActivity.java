package rohksin.com.alarmmanagerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Illuminati on 11/6/2017.
 */

public class SecondActivity extends AppCompatActivity {

    private TextView textView ;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        textView = (TextView)findViewById(R.id.textView);

        String text = getIntent().getStringExtra("SomeValue");
        textView.setText("From AlarmManager "+text);

    }



}
