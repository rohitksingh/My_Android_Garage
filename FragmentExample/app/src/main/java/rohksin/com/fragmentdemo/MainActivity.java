package rohksin.com.fragmentdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;

    private Button addFragment;
    private Button replaceFragment;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment = (Button)findViewById(R.id.addFragmet);
        replaceFragment = (Button)findViewById(R.id.replaceFragmet);
        manager = getSupportFragmentManager();

        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });

        replaceFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
            }
        });

    }


    private void increment()
    {
        count++;
    }

    private void addFragment()
    {
        increment();
        manager.beginTransaction()
                .add(R.id.fragmentContainer,getFragment(count+""))
                .commit();
    }

    private void replaceFragment()
    {
        increment();
        manager.beginTransaction()
                .replace(R.id.fragmentContainer,getFragment(count+""))
                .commit();
    }

    public MyFragment getFragment(String text)
    {
        return MyFragment.createInstance(text);
    }


}
