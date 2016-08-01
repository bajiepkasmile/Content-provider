package com.nodomain.viewer.di.modules;

import com.nodomain.viewer.domain.DataManager;
import com.nodomain.viewer.domain.interactors.LoadArtistsInteractor;

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
