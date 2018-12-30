package rohitksingh.com.activityrelatedstuff;

import android.app.Activity;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    public static String KEY = "ohitksingh.com.activityrelatedstuff.SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);



        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("This demo is for sending data between Activities Click to see the demo");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this,SecondSupportActivity.class);
                intent.putExtra(KEY,"This value is sent from First Activity");
                startActivityForResult(intent,9000);


            }
        });

    }


    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent intent)
    {
        if(reqCode==9000)
        {
            if(resCode== Activity.RESULT_OK)
            {
                String value = intent.getStringExtra("EDIT_TEXT_VALUE");
                textView.setText(value);
            }
            else if(resCode== Activity.RESULT_CANCELED){

                Toast.makeText(SecondActivity.this,"No result sent back", Toast.LENGTH_LONG).show();

            }
        }
    }
}
