package com.omdb.rohksin.transitionapi;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 7/1/2017.
 */
public class ListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        setAnimation();
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<String> names = createList();
        ListAdapter adapter = new ListAdapter(names,this);
        rv.setAdapter(adapter);

    }

    public List<String> createList()
    {
        List<String> names = new ArrayList<String >();
        for(int i=0;i<100;i++)
        {
            names.add("ABC"+i);
        }

        return names;
    }


    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20)
        {
            Slide slide = new Slide();
            slide.setDuration(1000);
            Log.d("Slide", (slide.getSlideEdge()) + "");
            slide.setSlideEdge(Gravity.LEFT);                                   // <- To specify the edge from view is coming from and going to
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }
}
