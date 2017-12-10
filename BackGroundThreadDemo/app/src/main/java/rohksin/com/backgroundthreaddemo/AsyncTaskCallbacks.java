package rohksin.com.backgroundthreaddemo;

/**
 * Created by Illuminati on 12/10/2017.
 */

public interface AsyncTaskCallbacks {

    public void onPreExecute();

    public void onProgressUpdate(Integer progress);

    public void doInBackground();

    public void onPostExecute();

}
