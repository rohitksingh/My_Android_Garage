package rohitksingh.com.activityrelatedstuff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondSupportActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_support_activity);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        String fromActivity = getIntent().getStringExtra(SecondActivity.KEY);

        editText.setText(fromActivity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataBack();

            }
        });


    }

    private void sendDataBack()
    {
        Intent resultBack = new Intent();
        resultBack.putExtra("EDIT_TEXT_VALUE",editText.getText().toString());
        setResult(Activity.RESULT_OK,resultBack);
        finish();
    }
}
