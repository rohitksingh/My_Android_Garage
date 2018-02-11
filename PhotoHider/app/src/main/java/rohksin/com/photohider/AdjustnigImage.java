package rohksin.com.photohider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Illuminati on 2/11/2018.
 */

public class AdjustnigImage extends ImageView {


    public AdjustnigImage(Context context) {
        super(context);
    }

    public AdjustnigImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustnigImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int a, int b)
    {
        super.onMeasure(a,b);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
    }

}
