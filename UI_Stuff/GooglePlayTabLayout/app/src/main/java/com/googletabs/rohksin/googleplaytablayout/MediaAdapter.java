package com.googletabs.rohksin.googleplaytablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

/**
 * Created by Illuminati on 4/16/2017.
 */
public class MediaAdapter extends FragmentPagerAdapter {

    private int PageCount;
    private String[] PageTitle;
    private String[] PageDescription;
    private Context context;

    public MediaAdapter(FragmentManager fm, Context context, int PageCount, String[] PageTitle,String[] PageDescription) {

        super(fm);
        this.context = context;
        this.PageCount = PageCount;
        this.PageTitle = PageTitle;
        this.PageDescription = PageDescription;


    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(MediaFragment.PAGE_DESC,PageDescription[position]);
        Fragment fragment;
        fragment= MediaFragment.newInstance(position+1);
        fragment.setArguments(bundle);
        Log.d("Rohit", "returning fragment");
        return fragment;
    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return PageTitle[position];
    }
}
