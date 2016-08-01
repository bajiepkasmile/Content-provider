package com.nodomain.loader.di.modules;

import com.nodomain.loader.domain.DataManager;
import com.nodomain.loader.domain.interactors.LoadArtistsInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = AppModule.class
)
public class InteractorsModule {

    @Singleton
    @Provides
    LoadArtistsInteractor provideLoadInteractor(DataManager dataManager) {
        return new LoadArtistsInteractor(dataManager);
    }

}
