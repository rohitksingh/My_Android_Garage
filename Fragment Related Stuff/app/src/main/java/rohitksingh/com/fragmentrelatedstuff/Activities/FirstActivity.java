package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rohitksingh.com.fragmentrelatedstuff.Fragments.UserNameFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.WelcomeUserFragment;
import rohitksingh.com.fragmentrelatedstuff.Listener.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class FirstActivity extends AppCompatActivity implements NextButtonListener {


    private FragmentManager fragmentManager;
    private Fragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState==null) {

            userFragment = UserNameFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.profile, userFragment, "TAG").commit();
        }
        else {
            userFragment = fragmentManager.findFragmentByTag("TAG");
        }

    }


    /******************************************************************
     *   Listener
     ******************************************************************/

    @Override
    public void nextButtonClicked(String username) {

        Toast.makeText(FirstActivity.this, "Next Button Clicked", Toast.LENGTH_LONG).show();
        addWelcomeFragment(username);

    }

    /******************************************************************
     *   Private Methods
     ******************************************************************/

    private void addUserValidationFragment()
    {
        Fragment fragment = UserNameFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.profile,fragment).commit();
    }

    private void addWelcomeFragment(String username)
    {
        Fragment fragment = WelcomeUserFragment.getInstance("Welcome "+username);
        fragmentManager.beginTransaction().replace(R.id.profile,fragment).commit();
    }


}
