package com.googletabs.rohksin.googleplaytablayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Illuminati on 4/16/2017.
 */
public class MediaFragment extends Fragment {


    String name;
    private int mPage;
    private static final String ARG_PAGE = "ARG_PAGE";
    public static final String PAGE_DESC ="PAGE_DESC";


    public static MediaFragment newInstance(int page) {

        Log.d("Rohit", "new Instance");
        Bundle args = new Bundle();

        args.putInt(ARG_PAGE, page);
        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        Log.d("Rohit", "on Create");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d("Rohit", "On create View");
       View view = inflater.inflate(R.layout.media_display_fragment,container,false);
        RelativeLayout framentLayout = (RelativeLayout)view;

        //Here acess the elements of fragment
        name = getArguments().getString(PAGE_DESC);
        Log.d("Rohit","Passed Value "+name);
        TextView name_frag = (TextView)framentLayout.findViewById(R.id.fragmentName);
        name_frag.setText(name);
        return view;
    }

}
