package rohitksingh.com.activityrelatedstuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrientationChangeActivity extends AppCompatActivity {


    private Button button;
    private TextView textView;

    private String STRING_VALUE_KEY = "rohitksingh.com.activityrelatedstuff.OrientationChangeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        if(savedInstanceState!=null)
        {
            String value = savedInstanceState.getString(STRING_VALUE_KEY);
            textView.setText(value);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("This value will be saved and retained while orientation chamge");
            }
        });

    }


    // Must override this method to handle Orientation change
    @Override
    protected void onSaveInstanceState(Bundle outBundle)
    {
        super.onSaveInstanceState(outBundle);
        outBundle.putString(STRING_VALUE_KEY,textView.getText().toString());
    }
}
