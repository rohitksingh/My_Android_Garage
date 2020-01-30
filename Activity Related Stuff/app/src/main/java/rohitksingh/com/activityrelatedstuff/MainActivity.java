package rohitksingh.com.activityrelatedstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    /************************************************************************************************

     THis demo showcases the Activity Callback methods which are invoked by the System when
     an Activity goes from one state to another

     ***********************************************************************************************/

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("Callback","onCreateView");
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FirstActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }


    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("Callback","onStart()");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("Callback","onResume()");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("Callback","onPause()");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("Callback","onStop()");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("Callback","onDestroy()");
    }


    /***********************************************************************************************

     onSaveInstanceState()

     This method is not guaranteed to be invoked by System all the time when an Activity instance is
     destroyed. So be careful

     ***********************************************************************************************/

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        Log.d("Callback","onSaveInstanceState()");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.d("Callback","onRestoreInstanceState()");
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.d("Callback","onRestart");
    }

}

    /***********************************************************************************************

     Activity has four state

     1) Running      - When an activity is actively interacting with user
     2) Paused       - When an activity looses focus (It is still visible to user) eg Dialog obscures an Activity
     3) Stopped      - When another activity completely obscures Activity
     4) Destroyed    - When System/User kills the activity (To free up memory)


     This is the order of Activity

     onCreate() -->  onStart() ---> onResume()
     onPause() ----> onStop()  ----> onDestroyed()

     What happens when

     a) User presses back button?
     b) Home Button is clicked?
     c) User navigates to Another activity?
     d) Activity moves to Multipane Activity?
     e) User switches from one App to Another in multipane Activity?
     f) User navigates to Another app eg Picks up a phone and comes back
     g) When App remains in Background for a long time
     h) When a dialog or a non full-sized Activity obscures an Activity?
     i) When a dialog or an Activity completely hides another activity?

    ************************************************************************************************/