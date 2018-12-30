package rohitksingh.com.activityrelatedstuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private String TEXT_VIEW_KEY = "rohitksingh.com.activityrelatedstuff.FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        if(savedInstanceState!=null)
        {
            String value = savedInstanceState.getString(TEXT_VIEW_KEY);
            textView.setText(value);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("This value will be retained while orientation change");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outBundle)
    {
        super.onSaveInstanceState(outBundle);
        outBundle.putString(TEXT_VIEW_KEY,textView.getText().toString());
    }
}
