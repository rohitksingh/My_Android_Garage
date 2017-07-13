package com.omdb.rohksin.sos;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Illuminati on 7/13/2017.
 */
public class SOSView extends LinearLayout {


    public SOSView(Context context) {
        super(context);
        initialize(context);
    }

    public SOSView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SOSView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public void initialize(Context context)
    {
        inflate(context,R.layout.sos_view,this);
    }
}
