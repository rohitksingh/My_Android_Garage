package rohitksingh.com.activityrelatedstuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {



    /*

        This Demo showcases the orientation change

        SAVING PART

        since super.onSaveInstanceState(outBundle); is commented System will not save Text in EditText which is the default behaviour

     */

    private Button button;
    private EditText editText;
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

    /*
                                             SAVING STATE

         onSaveInstanceState();
         This is for saving Actiivity instance when Which will be used by System while recreating a
         new instance of Activity

     */
    @Override
    protected void onSaveInstanceState(Bundle outBundle)
    {
        outBundle.putString(TEXT_VIEW_KEY,textView.getText().toString());
        //super.onSaveInstanceState(outBundle);    Comenting this would not save EditText value by default
    }


    /*
                                            RESTORING STATE

                        State can be restored by any of the method
                        a) onCreate(Bundle savedInstanceState)
                        b) onRestoreInstanceState(Bundle savedInstanceState)

                 Both methods get same Bundle objects. Only difference is that a null check is required in case of onCreate() method
     */

    /*
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String value = savedInstanceState.getString(TEXT_VIEW_KEY);
        textView.setText(value);
    }
    */

}
