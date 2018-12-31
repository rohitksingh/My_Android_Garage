package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import rohitksingh.com.fragmentrelatedstuff.Fragments.WelcomeUserFragment;
import rohitksingh.com.fragmentrelatedstuff.R;

public class SecondActivity extends AppCompatActivity {

    /********************************************************************************
     *
     * This Activity is to showcase Fragment Lifecycle callback methods
     *
     *******************************************************************************/

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        manager = getSupportFragmentManager();
        Fragment fragment = WelcomeUserFragment.newInstance("This demo showcases callback methods");
        manager.beginTransaction().add(R.id.profile,fragment).commit();

    }


}
