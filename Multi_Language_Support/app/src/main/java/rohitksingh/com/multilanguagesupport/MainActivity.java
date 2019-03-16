package rohitksingh.com.multilanguagesupport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView hindi, english, chinese, sampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleText = (TextView)findViewById(R.id.sampleText);
        sampleText.setText(R.string.about_android);


    }
}
