package com.omdb.rohksin.inheritance;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by Illuminati on 7/4/2017.
 */
public abstract class AnimationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(provideParentLayoutId());
        setAnimation();
    }

    public abstract int provideParentLayoutId();

    public abstract View setParentLayout();


    public void setAnimation()
    {

        final View parent = setParentLayout();
        parent.post(new Runnable() {
            @Override
            public void run() {
                int radius= (int)(Math.hypot(parent.getWidth(),parent.getHeight()));
                if(Build.VERSION.SDK_INT>20) {
                    Animator anim = ViewAnimationUtils.createCircularReveal(parent, parent.getRight(), parent.getBottom(), 0, radius);
                    anim.setDuration(1000);
                    anim.start();
                }
            }
        });

    }
}
