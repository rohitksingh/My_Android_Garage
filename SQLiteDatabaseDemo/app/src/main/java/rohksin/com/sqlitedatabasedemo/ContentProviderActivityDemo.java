package rohksin.com.sqlitedatabasedemo;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

        List<Music> musicList = getAllMusicFiles();

        for (int i=0;i<musicList.size();i++)
        {
            Log.d("Num of files",musicList.get(i).toString());
        }


    }


    public ArrayList<Music> getAllMusicFiles()
    {
        Cursor musicCursor = getContentResolver().query(MUSIC_FILE_LOCATION,proj, null, null, null, null);
        int noOfResults = musicCursor.getCount();
        musicFiles = new ArrayList<Music>();

        for(int i=0;i<noOfResults;i++)
        {
            musicCursor.moveToNext();
            Music music = new Music();
            music.setDisplayName(musicCursor.getString(musicCursor.getColumnIndex(IMAGE_NAME)));
            music.setCreatedDate(musicCursor.getString(musicCursor.getColumnIndex(DATE_CREATED)));
            musicFiles.add(music);
        }

        return musicFiles;
    }
}
