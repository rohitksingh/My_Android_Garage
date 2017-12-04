package rohksin.com.backgroundthreaddemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text);

        //new TimerTask().execute(10);

        String[] names = new String[]{"Ram", "Shyam", "Ghanshyam", "kalam"};
        new DummyTask().execute(names);

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
