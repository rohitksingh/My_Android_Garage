package rohksin.com.sqlitedatabasedemo;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 12/9/2017.
 */

public class ContentProviderActivityDemo extends AppCompatActivity{

    private Uri MUSIC_FILE_LOCATION = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    private String IMAGE_NAME = MediaStore.Audio.Media.DISPLAY_NAME;
    private String DATE_CREATED = MediaStore.Audio.Media.DATE_MODIFIED;
    private String[] proj = new String[]{IMAGE_NAME,DATE_CREATED};

    private ArrayList<Music> musicFiles;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        myCursorTest();       //<-------- getting data from custom contentprovider


        /*
        List<Music> musicList = getAllMusicFilesUsingLoaders();

        for (int i=0;i<musicList.size();i++)
        {
            Log.d("Num of files",musicList.get(i).toString());
        }
        */


    }


    public List<Music> getAllMusicFiles()
    {
        Cursor musicCursor = getContentResolver().query(MUSIC_FILE_LOCATION,proj, null, null, null, null);

        return loadFromCursor(musicCursor);

    }


    public List<Music> getAllMusicFilesUsingLoaders()
    {

        CursorLoader cursorLoader = new CursorLoader(ContentProviderActivityDemo.this, MUSIC_FILE_LOCATION , proj , null, null,null);
        Cursor musicCursor = cursorLoader.loadInBackground();

        return loadFromCursor(musicCursor);
    }


    public List<Music> loadFromCursor(Cursor musicCursor)
    {
        musicFiles = new ArrayList<Music>();

        for(int i=0;i<musicCursor.getCount();i++)
        {
            musicCursor.moveToNext();
            Music music = new Music();
            music.setDisplayName(musicCursor.getString(musicCursor.getColumnIndex(IMAGE_NAME)));
            music.setCreatedDate(musicCursor.getString(musicCursor.getColumnIndex(DATE_CREATED)));
            musicFiles.add(music);
        }

        return musicFiles;
    }


    public void myCursorTest()
    {
        String PROVIDER_NAME = "rohksin.com.sqlitedatabasedemo.NameProvider";
        String URL = "content://"+PROVIDER_NAME+"/names";
        Uri CONTENT_URI = Uri.parse(URL);
        Cursor myCursor = getContentResolver().query(CONTENT_URI,null,null,null,null,null);
        Log.d("MyCustomProviderTest",myCursor.getCount()+"");
    }
}
