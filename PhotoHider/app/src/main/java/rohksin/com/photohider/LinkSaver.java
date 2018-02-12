package rohksin.com.photohider;

import android.content.Intent;
import android.net.Uri;
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
    private PhotoDatabase photoDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phot_save_layout);
        textView = (TextView)findViewById(R.id.count);
        photoDatabase = new PhotoDatabase(this);

        takeValueFunc();

    }

    public void takeValueFunc()
    {
        Log.d("Log","Inside value FUNC");

        Intent shareIntent = getIntent();
        if(shareIntent.getAction().equals(Intent.ACTION_SEND))
        {


            String text = shareIntent.getStringExtra(Intent.EXTRA_TEXT);


            //Uri uri  = shareIntent.getData();

            if(text!=null)
            {
                photoDatabase.addHistory(text);
            }

            Log.d("Log", "INside main finc" + text);
        }

    }
}
