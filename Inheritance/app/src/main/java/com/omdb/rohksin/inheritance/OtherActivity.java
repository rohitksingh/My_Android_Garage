package com.omdb.rohksin.inheritance;

import android.view.View;

/**
 * Created by Illuminati on 7/4/2017.
 */
public class OtherActivity extends AnimationActivity{


    @Override
    public int provideParentLayoutId() {
        return R.layout.other_layout;
    }

    @Override
    public View setParentLayout() {
        View parent = (View)findViewById(R.id.parentPanel);
        return parent;
    }
}
