package com.googletabs.rohksin.googleplaytablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

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
        return MediaFragment.newInstance(position+1,PageDescription[position]);
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
