package rohksin.com.jobschedulerdemo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static int JOB_ID = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            JobInfo myJobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyJobSevice.class))
                     .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)                                // Job runs when Device Connects to WIFI NETWORK
                     .build();

            JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(myJobInfo);


        }


    }


}
