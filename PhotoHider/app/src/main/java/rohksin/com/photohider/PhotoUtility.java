package rohksin.com.photohider;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.List;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class PhotoUtility {

    public static List<PhotoData> getImages(Context context)
    {

        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{};
        String selection = null;
        String groupBy = null;
        String orderBy = null;

        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);




        Cursor photoCursor = context.getContentResolver().query(imageUri,projection, null, null, null);

        Log.d("Total Count",photoCursor.getCount()+"");

        photoCursor.moveToFirst();

        for(int i =0;i<photoCursor.getCount();i++)
        {
            PhotoData data = new PhotoData();
            String path = photoCursor.getString(photoCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            Log.d("Path", path);
        }


        photoCursor.close();

        return null;
    }



}
