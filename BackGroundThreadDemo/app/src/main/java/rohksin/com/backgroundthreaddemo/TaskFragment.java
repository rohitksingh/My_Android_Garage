package rohksin.com.backgroundthreaddemo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Illuminati on 12/10/2017.
 */

public class TaskFragment extends Fragment {


    private AsyncTaskCallbacks callbacks;
    private DemoTask task;


    public static TaskFragment createInstance()
    {
        Bundle bundle = new Bundle();
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callbacks = (AsyncTaskRetainActivity)context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        callbacks = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment,parent,false);
        return view;
    }

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
        task = new DemoTask();
        task.execute();

    }



   private class DemoTask extends AsyncTask<Void,Integer,Void>
   {
       @Override
       public void onPreExecute()
       {
               callbacks.onPreExecute();
       }

       @Override
       protected Void doInBackground(Void... params) {

               for (int i = 10; i > 0; i--) {
                   publishProgress(i);
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           return null;
       }

       @Override
       public void onProgressUpdate(Integer... progress)
       {
             callbacks.onProgressUpdate(progress[0]);
       }

       @Override
       public void onPostExecute(Void result)
       {
              callbacks.onPostExecute();
       }
   }



}
