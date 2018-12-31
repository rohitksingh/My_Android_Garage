package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rohitksingh.com.fragmentrelatedstuff.Dialogs.StringReverseFragment;
import rohitksingh.com.fragmentrelatedstuff.R;

public class ThirdActivity extends AppCompatActivity {

    /*******************************************************
     * This Activity showcases
     *   1) Fragment state retention during orientation change
     *   2) Calling fragment method form Activity
     *   3) Use of Bundle
     *
     *******************************************************/

    private Button button1;

    private FragmentManager fragmentManager;
    private StringReverseFragment stringReverseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reverse_activity_layout);

        button1 = (Button)findViewById(R.id.leftButton);

        fragmentManager = getSupportFragmentManager();


        if(savedInstanceState==null) {

            stringReverseFragment = StringReverseFragment.getInstance("Random String");
            fragmentManager.beginTransaction()
                    .add(R.id.placeholder, stringReverseFragment, "FRAGMENT_TAG")
                    .commit();
        }else {
            stringReverseFragment = (StringReverseFragment) fragmentManager.findFragmentByTag("FRAGMENT_TAG");
        }


    }

}
