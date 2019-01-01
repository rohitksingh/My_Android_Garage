package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import rohitksingh.com.fragmentrelatedstuff.Listener.ContollerListerner;
import rohitksingh.com.fragmentrelatedstuff.Fragments.CounterControllerFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.TextDisplayFragment;
import rohitksingh.com.fragmentrelatedstuff.R;

public class FragmentCommunicationActivity extends AppCompatActivity implements ContollerListerner{

    /***********************************************************************
     *           This Activity showcases communication b/w fragments
     *           Fragments keep state while orientation change
     *
     ***********************************************************************/


    private FragmentManager manager;
    private TextDisplayFragment counterDisplayfragment;
    private CounterControllerFragment controllerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comm_activity_layout);
        getSupportActionBar().setTitle("Fragment Communication");
        manager = getSupportFragmentManager();

        if(savedInstanceState==null) {
            counterDisplayfragment = TextDisplayFragment.getInstance();
            controllerFragment = CounterControllerFragment.getInstance();
            manager.beginTransaction()
                    .add(R.id.fragment1, counterDisplayfragment, "DISPLAY_FRAGMENT")
                    .add(R.id.fragment2, controllerFragment, "CONTROLLER_FRAGMENT")
                    .commit();
        }else {
            counterDisplayfragment = (TextDisplayFragment) manager.findFragmentByTag("DISPLAY_FRAGMENT");
            controllerFragment     = (CounterControllerFragment)manager.findFragmentByTag("CONTROLLER_FRAGMENT");
        }


    }

    /****************************************************************
     *         LISTENER
     ****************************************************************/

    @Override
    public void leftButtonClicked() {
        counterDisplayfragment.increment();
    }

    @Override
    public void rightButtonClicked() {
        counterDisplayfragment.decrement();
    }
}
