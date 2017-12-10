package rohksin.com.backgroundthreaddemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Illuminati on 12/10/2017.
 */

public class AsyncTaskRetainActivity extends AppCompatActivity implements AsyncTaskCallbacks{

    private TextView textView;
    private FragmentManager fragmentManager;
    private TaskFragment taskFragment;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retain_task_activity);
        textView = (TextView)findViewById(R.id.retaindText);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState==null) {
            taskFragment = new TaskFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, taskFragment)
                    .commit();
        }

    }

    @Override
    public void onPreExecute() {
        textView.setText("Startting");
    }

    @Override
    public void onProgressUpdate(Integer progress) {

        textView.setText(progress+"");
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onPostExecute() {
        textView.setText("Completed");
    }
}
