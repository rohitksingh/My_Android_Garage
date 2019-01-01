package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import rohitksingh.com.fragmentrelatedstuff.Fragments.CounterControllerFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.TextDisplayFragment;
import rohitksingh.com.fragmentrelatedstuff.R;

public class FragmentCommunicationActivity extends AppCompatActivity {


    private FragmentManager manager;
    private Fragment counterDisplayfragment;
    private Fragment controllerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comm_activity_layout);
        getSupportActionBar().setTitle("Fragment Communication");

        counterDisplayfragment = TextDisplayFragment.getInstance();
        controllerFragment = CounterControllerFragment.getInstance();

        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.fragment1,counterDisplayfragment)
                .add(R.id.fragment2,controllerFragment)
                .commit();

    }


}
