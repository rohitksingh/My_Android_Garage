package rohksin.com.backgroundthreaddemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text);




        Log.d("Grace","grace"+(task==null));


        /*
         AsyncTask Demo
         */
        task =  new TimerTask();
        task.execute(10);


        //String[] names = new String[]{"Ram", "Shyam", "Ghanshyam", "kalam"};
       // new DummyTask().execute(names);


        ///////////////////////////////////////////
               //  Handler demo
        //////////////////////////////////////////////////////////////////

        /*
        final Handler handler = new Handler();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<10;i++)
                        {
                            textView.setText("Handler "+i);

                            try {
                                Thread.sleep(1000);                       //<---------- It causes Main UI Thread sleep ?
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
  */


        //////////////////////////////////////////////////////////////////
                 //Handler demo finished
        //////////////////////////////////////////////////////////////////

      /*
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        for(int i=0;i<10;i++) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            textView.setText(i);
                        }
                    }
                });
            }
        });
*/




    }


    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("Task",(task.isCancelled()==true)+"");
        task.cancel(false);
        Log.d("Task",(task.isCancelled()==true)+"");
    }


    public class TimerTask extends AsyncTask<Integer, Integer, String>{


        @Override
        public void onPreExecute()
        {
            textView.setText("Starting");
        }

        @Override
        protected String doInBackground(Integer... integers) {

            int value = integers[0];

            for(int i= value;i>0;i--)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);

            }


            return "finished";
        }


        @Override
        public void onProgressUpdate(Integer... integer)
        {
            textView.setText(integer[0]+"");
        }

        @Override
        public void onPostExecute(String msg)
        {
            textView.setText(msg);
        }




    }


    public class DummyTask extends AsyncTask<String, String, Integer>
    {
        @Override
        public void onPreExecute()
        {
            textView.setText("Launch");
        }

        @Override
        protected Integer doInBackground(String... input) {

            for(int i=0;i<input.length;i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(input[i]);
            }

            return 1001;                 //<-----------     Integer result
        }

        @Override
        public void onProgressUpdate(String... params)
        {
            textView.setText(params[0]);
        }

        @Override
        public void onPostExecute(Integer result)
        {
            textView.setText(result+"");
        }



    }






}
