package rohksin.com.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by Illuminati on 11/6/2017.
 */

public class MyJobSevice extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {

        createANewThreadForJob(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    public void createANewThreadForJob(final JobParameters jobParameters)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                performJob(jobParameters);
            }
        }).start();
    }

    public void performJob(JobParameters jobParameters)
    {


        Log.d("JobScheduler","Job Started");

        int count = 10;
        while (true)
        {

            Log.d("JobScheduler",count+"");

            if(count ==0)
                break;
            else
                count--;
        }




    }
}
