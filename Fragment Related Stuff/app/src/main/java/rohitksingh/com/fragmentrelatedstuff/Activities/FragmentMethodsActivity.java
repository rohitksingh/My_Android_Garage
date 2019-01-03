package rohitksingh.com.fragmentrelatedstuff.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import rohitksingh.com.fragmentrelatedstuff.Fragments.CounterControllerFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.StringReverseFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.TextDisplayFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.UserNameFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.WelcomeUserFragment;
import rohitksingh.com.fragmentrelatedstuff.Listener.ContollerListerner;
import rohitksingh.com.fragmentrelatedstuff.Listener.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class FragmentMethodsActivity extends AppCompatActivity implements NextButtonListener, ContollerListerner{


    private Button add, replace, remove, addTobackStack;
    private FragmentManager manager;

    private int index=0;
    private List<Fragment> allFragments;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_methods_layout);
        add = (Button)findViewById(R.id.add);
        replace = (Button)findViewById(R.id.replace);
        remove = (Button)findViewById(R.id.remove);
        addTobackStack = (Button)findViewById(R.id.removeAll);
        manager = getSupportFragmentManager();

        allFragments = getFragments();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(allFragments.get(index),"TAG "+index);
                index++;
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        addTobackStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBackStack(allFragments.get(index),"TAG "+index);
                index++;
            }
        });

    }


    private void addFragment(Fragment fragment,String TAG)
    {
        manager.beginTransaction()
                .add(R.id.placeholder,fragment,TAG)
                .commit();
    }


    private void replaceFragment()
    {
        Fragment replaceFragment = WelcomeUserFragment.getInstance("This fragment is created by Replace method");
        manager.beginTransaction()
                .replace(R.id.placeholder,replaceFragment,"Replace method")
                .commit();
    }

    private void removeFragment()
    {
       manager.beginTransaction()
               .remove(allFragments.get(0))
               .commit();
    }

    private void addToBackStack(Fragment fragment, String TAG)
    {
        manager.beginTransaction()
                .add(R.id.placeholder,fragment,TAG)
                .addToBackStack(TAG)
                .commit();
    }


    @Override
    public void nextButtonClicked(String username) {

    }


    private List<Fragment> getFragments()
    {
        List<Fragment> list = new ArrayList<Fragment>();
        Fragment frag1 = UserNameFragment.newInstance();
        Fragment frag2 = StringReverseFragment.getInstance("This is fragment 2");
        Fragment frag3 = WelcomeUserFragment.getInstance("This is fragment3");
        Fragment frag4 = CounterControllerFragment.getInstance();

        list.add(frag1);
        list.add(frag2);
        list.add(frag3);
        list.add(frag4);
        return list;
    }

    @Override
    public void leftButtonClicked() {

    }

    @Override
    public void rightButtonClicked() {

    }
}
