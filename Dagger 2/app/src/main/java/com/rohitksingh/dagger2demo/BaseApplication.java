package com.rohitksingh.dagger2demo;

import com.rohitksingh.dagger2demo.di.AppComponent;
import com.rohitksingh.dagger2demo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
