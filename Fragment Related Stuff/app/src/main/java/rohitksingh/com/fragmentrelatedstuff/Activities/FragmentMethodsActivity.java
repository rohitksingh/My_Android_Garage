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

import rohitksingh.com.fragmentrelatedstuff.Fragments.TextDisplayFragment;
import rohitksingh.com.fragmentrelatedstuff.Fragments.WelcomeUserFragment;
import rohitksingh.com.fragmentrelatedstuff.R;

public class FragmentMethodsActivity extends AppCompatActivity{


    private Button add, replace, remove, removeAll;
    private FragmentManager manager;

    //private Fragment textFragment;

    private int index=0;
    private List<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_methods_layout);
        add = (Button)findViewById(R.id.add);
        replace = (Button)findViewById(R.id.replace);
        remove = (Button)findViewById(R.id.remove);
        removeAll = (Button)findViewById(R.id.removeAll);

        manager = getSupportFragmentManager();

        list = getAllFragment();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("INDEX",index+"");
                addFragment(list.get(index));
                index++;
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void addFragment(String msg)
    {
        Fragment fragment = WelcomeUserFragment.newInstance(msg);
        manager.beginTransaction()
                .add(R.id.placeholder,fragment)
                .addToBackStack("msg")
                .commit();
    }


    private void replaceFragment()
    {
    }

    private void removeFragment()
    {

    }

    private void removeAllFragment()
    {

    }


    private List<String> getAllFragment()
    {
        List<String> list = new ArrayList<String>();

        for(int i=0;i<10;i++)
        {
            list.add("This is fragment num "+(i+1));
        }

        return list;
    }

}
