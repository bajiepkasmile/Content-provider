package com.nodomain.viewer.di.components;

import com.nodomain.viewer.di.modules.AppModule;
import com.nodomain.viewer.di.modules.InteractorsModule;
import com.nodomain.viewer.ui.activities.ArtistDetailsActivity;
import com.nodomain.viewer.ui.activities.ArtistsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                InteractorsModule.class
        }
)
public interface AppComponent {

    void inject(ArtistsListActivity activity);

    void inject(ArtistDetailsActivity activity);

}
