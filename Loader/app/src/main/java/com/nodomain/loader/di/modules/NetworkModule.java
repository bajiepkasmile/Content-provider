package com.nodomain.loader.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nodomain.loader.domain.datasources.network.ApiConstants;
import com.nodomain.loader.domain.datasources.network.ArtistDeserializer;
import com.nodomain.loader.domain.datasources.network.YandexService;
import com.nodomain.loader.model.Artist;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    YandexService provideYandexService() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(Artist.class, new ArtistDeserializer()).create();

        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(YandexService.class);
    }

}
