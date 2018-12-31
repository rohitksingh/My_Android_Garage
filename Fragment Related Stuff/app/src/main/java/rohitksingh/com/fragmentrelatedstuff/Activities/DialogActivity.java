package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rohitksingh.com.fragmentrelatedstuff.Dialogs.ValidationDialog;
import rohitksingh.com.fragmentrelatedstuff.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class DialogActivity extends AppCompatActivity implements NextButtonListener {

    private Button leftButton;
    private Button rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);
        leftButton = (Button)findViewById(R.id.leftButton);
        rightButton = (Button)findViewById(R.id.rightButton);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimppleDialog();
            }
        });

    }


    @Override
    public void nextButtonClicked(String username) {
        Toast.makeText(DialogActivity.this,username, Toast.LENGTH_SHORT).show();
    }

    private void showSimppleDialog()
    {
        ValidationDialog dialog = new ValidationDialog(DialogActivity.this);
        dialog.show();
    }


}
