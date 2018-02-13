package rohksin.com.photohider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class PhotoListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private PhotoAdapter adapter;

    private PhotoDatabase photoDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list_activity);

        initDataBase();

        rv = (RecyclerView)findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(this,2);
        //layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new PhotoAdapter(this, getSavedLinks());
        //LinkAdapter adapter = new LinkAdapter(this, getSavedLinks());
        rv.setAdapter(adapter);

        //PhotoUtility.getImages(this);

    }



    public List<PhotoData> getDummyList()
    {
        List<PhotoData> list = new ArrayList<PhotoData>();

        PhotoData data = new PhotoData();
        data.setPath("Dummy");

        for(int i=0;i<10;i++)
        {
            list.add(data);
        }

        return list;

    }


    public List<PhotoData> getSavedLinks()
    {
        List<PhotoData> savedLinks = photoDatabase.getHistoryList();

        if(savedLinks== null)
        {
            return getDummyList();
        }
        else {
            return savedLinks;
        }

    }

    private void initDataBase()
    {
        photoDatabase = new PhotoDatabase(this);
    }




}
