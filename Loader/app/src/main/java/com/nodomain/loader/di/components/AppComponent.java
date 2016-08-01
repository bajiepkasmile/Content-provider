package com.nodomain.loader.di.components;

import com.nodomain.loader.di.modules.AppModule;
import com.nodomain.loader.di.modules.InteractorsModule;
import com.nodomain.loader.di.modules.StorageModule;
import com.nodomain.loader.domain.providers.ArtistsProvider;
import com.nodomain.loader.ui.activities.LoadActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                InteractorsModule.class,
                StorageModule.class
        }
)
public interface AppComponent {

    void inject(LoadActivity activity);

    void inject(ArtistsProvider provider);

}
