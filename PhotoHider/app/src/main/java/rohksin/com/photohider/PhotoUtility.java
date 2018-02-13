package rohksin.com.photohider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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


    public static void openLinkInBrowser(Context context, String link)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        context.startActivity(i);
    }


    public static void saveImageInPrivate(Context context, Uri uri)
    {

        Log.d("Uri ",uri.toString());

        Intent intent  = new Intent(context, ImageDownloadService.class);



        intent.putExtra("IMAGE_URI",uri);

        //intent.putExtra(DOWNLOAD_SERVICE_URL, url);
        //intent.putExtra(DOWNLOAD_SERVICE_FILE_NAME, fileName+".mp3");
        context.startService(intent);
    }




    public static void downloadFile(Context context, String sUrl, String fileName)
    {
        Log.d("KK", sUrl);
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(sUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d("Server returned HTTP",connection.getResponseCode()
                        + " " + connection.getResponseMessage());
            }

            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            File musicFile = new File(context.getFilesDir(), fileName);
            output = new FileOutputStream(musicFile);

            byte data[] = new byte[4096];
            long total = 0;
            int count;

            Log.d("TOTAL SIZE",sUrl);

            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                /*
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                */
                total += count;

                Log.d("File size",total+"");
                // publishing the progress....
                //  if (fileLength > 0) // only if total length is known
                // publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {

            Log.d("KK", e.getMessage());
            e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
    }




    public static File getMainExternalFolder()
    {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "MainFolder");

        File file1 = Environment.getRootDirectory();


        if (!file.mkdirs()) {
            Log.e("Directory not created", "Directory not created");
        }
        return file;
    }




}
