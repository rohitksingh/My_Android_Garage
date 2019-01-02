package rohitksingh.com.fragmentrelatedstuff.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import rohitksingh.com.fragmentrelatedstuff.Fragments.BasicFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.ChildFragment1;
import rohitksingh.com.fragmentrelatedstuff.R;

public class InheritanceFragmentActivity extends AppCompatActivity {


    private FragmentManager manager;
    private BasicFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        fragment = new ChildFragment1();

        //Passing  data to Fragment

        Bundle args = new Bundle();
        args.putString("MSG","This is inheritance example");
        fragment.setArguments(args);

        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.profile,fragment,"TAG")
                .commit();

    }

}
