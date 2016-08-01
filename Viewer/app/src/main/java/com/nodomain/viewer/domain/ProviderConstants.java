package com.nodomain.viewer.domain;

import android.net.Uri;

public class ProviderConstants {

    public static final String AUTHORITY = "com.nodomain.loader";
    public static final String PATH_ARTISTS = "artists";
    public static final String PATH_GENRES = "genres";

    public static final Uri ARTISTS_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_ARTISTS);
    public static final Uri GENRES_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_GENRES);

    //Columns
    public static final String COLUMN_IDS = "ids";
    public static final String COLUMN_NAMES = "names";
    public static final String COLUMN_TRACKS = "tracks";
    public static final String COLUMN_ALBUMS = "albums";
    public static final String COLUMN_LINKS = "links";
    public static final String COLUMN_DESCRIPTIONS = "description";
    public static final String COLUMN_SMALL_COVERS_URLS = "small_covers_urls";
    public static final String COLUMN_BIG_COVERS_URLS = "big_covers_urls";

}
