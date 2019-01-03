package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rohitksingh.com.fragmentrelatedstuff.R;

public class MainActivity extends AppCompatActivity {

    /******************************************************************
     *  This is the main Activity
     *  Change Target Activity to see other demo
     ******************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, FragmentMethodsActivity.class));

    }

}
