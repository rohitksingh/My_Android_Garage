package rohksin.com.photohider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.logging.Logger;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class LinkSaver extends AppCompatActivity{


    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phot_save_layout);
        textView = (TextView)findViewById(R.id.count);
        takeValueFunc();

    }

    public void takeValueFunc()
    {
        Log.d("Log","Inside value FUNC");
    }
}
