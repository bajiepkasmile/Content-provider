package com.nodomain.loader.di.modules;

import android.content.Context;

import com.nodomain.loader.domain.DataManager;
import com.nodomain.loader.domain.datasources.network.YandexService;
import com.nodomain.loader.domain.datasources.storage.DbManager;
import com.nodomain.loader.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                NetworkModule.class,
                StorageModule.class
        }
)
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
    DataManager provideDataManager(YandexService service, DbManager dbManager) {
        return new DataManager(service, dbManager);
    }

    @Singleton
    @Provides
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context);
    }

}
