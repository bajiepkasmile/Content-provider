package com.nodomain.viewer.di.modules;

import android.content.Context;

import com.nodomain.viewer.domain.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    DataManager provideDataManager(Context context) {
        return new DataManager(context);
    }

}
