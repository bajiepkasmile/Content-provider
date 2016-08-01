package com.nodomain.loader.di.modules;

import android.content.Context;

import com.nodomain.loader.domain.datasources.storage.DbHelper;
import com.nodomain.loader.domain.datasources.storage.DbManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Singleton
    @Provides
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Singleton
    @Provides
    DbManager provideDbManager(DbHelper dbHelper) {
        return new DbManager(dbHelper);
    }

}
