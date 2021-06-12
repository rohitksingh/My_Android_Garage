package com.rohitksingh.dagger2demo.di;

import com.rohitksingh.dagger2demo.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}
