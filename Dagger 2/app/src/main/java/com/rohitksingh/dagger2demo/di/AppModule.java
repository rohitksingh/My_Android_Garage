package com.rohitksingh.dagger2demo.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.rohitksingh.dagger2demo.R;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;

/**
 * This is where all the Application level modules are kept. For eg: Retrofit, Glide, etc
 */
@Module
public class AppModule {

    @Provides
    static String initialName(){
        return "This is initial name of the String";
    }

    @Provides
    static RequestOptions provideRequestOption(){
        return RequestOptions.placeholderOf(R.drawable.cat_logo);
    }

    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable provideAppDrawables(Application application){
        return ContextCompat.getDrawable(application, R.drawable.cat_logo);
    }
}
