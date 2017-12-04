package rohksin.com.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String tag = "activityLifeCycle";
    private EditText text;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag,"Create");
        text = (EditText)findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Button clicked");
            }
        });
    }



    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(tag,"Start");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(tag,"Resume");
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.d(tag,"Restart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(tag,"Pause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(tag,"Stop");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(tag,"Destroy");
    }



}
