package com.nodomain.loader.domain.datasources.network;

import com.nodomain.loader.model.Artist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YandexService {

    @GET("artists.json")
    Call<ArrayList<Artist>> getArtists();

}
