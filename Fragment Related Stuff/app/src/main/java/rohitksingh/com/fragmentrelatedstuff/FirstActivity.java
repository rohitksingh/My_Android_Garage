package rohitksingh.com.fragmentrelatedstuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements NextButtonListener{


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = UserNameFragment.newInstance();

        fragmentManager.beginTransaction().add(R.id.profile,fragment).commit();



    }

    @Override
    public void nextButtonClicked() {
        Toast.makeText(FirstActivity.this, "Next Button Clicked", Toast.LENGTH_LONG).show();
    }
}
