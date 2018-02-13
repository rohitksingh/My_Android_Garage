package rohksin.com.photohider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class LinkSaver extends AppCompatActivity{



    private ImageView saveImage;
    private PhotoDatabase photoDatabase;

    private Button cancel;
    private Button save;

    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phot_save_layout);
        saveImage = (ImageView)findViewById(R.id.saveImage);
        photoDatabase = new PhotoDatabase(this);
        save = (Button)findViewById(R.id.save);
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(imageUri);
            }
        });

        loadImage();

    }

    public void loadImage()
    {
        Log.d("Log","Inside value FUNC");

        Intent shareIntent = getIntent();
        if(shareIntent.getAction().equals(Intent.ACTION_SEND))
        {


            String mimeType = shareIntent.getType();

            String url = null;

            if(mimeType.startsWith("text/plain"))
            {
                url = shareIntent.getStringExtra(Intent.EXTRA_TEXT);
            }
            else if(mimeType.startsWith("image/"))
            {


                imageUri = (Uri)shareIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                url = imageUri.toString();
                Picasso.with(LinkSaver.this)
                        .load(imageUri)
                        .into(saveImage);

                Log.d("xx",imageUri+"");

            }


            if(url!=null)
            {
                photoDatabase.addHistory(url);
            }

            Log.d("Log", "INside main finc" + mimeType + ": "+ url);
        }

    }


    public void saveImage(Uri uri)
    {
        PhotoUtility.saveImageInPrivate(LinkSaver.this,uri);
    }


}
