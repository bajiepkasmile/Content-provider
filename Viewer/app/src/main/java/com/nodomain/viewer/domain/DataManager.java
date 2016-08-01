package com.nodomain.viewer.domain;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.nodomain.viewer.model.Artist;

import java.util.ArrayList;

public class DataManager {

    Context context;
    private ArrayList<Artist> artists;

    public DataManager(Context context) {
        this.context = context;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void loadArtists() throws NullPointerException {
        artists = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();

        Cursor artistsCursor = contentResolver.query(ProviderConstants.ARTISTS_URI, null, null, null, null);
        Cursor genresCursor = null;

        int columnIds = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_IDS);
        int columnNames = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_NAMES);
        int columnTracks = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_TRACKS);
        int columnAlbums = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_ALBUMS);
        int columnLinks = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_LINKS);
        int columnDescriptions = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_DESCRIPTIONS);
        int columnSmallCovers = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_SMALL_COVERS_URLS);
        int columnBigCovers = artistsCursor.getColumnIndex(ProviderConstants.COLUMN_BIG_COVERS_URLS);

        artistsCursor.moveToFirst();
        while (!artistsCursor.isAfterLast()) {
            Artist artist = new Artist();

            artist.setId(artistsCursor.getLong(columnIds));
            artist.setName(artistsCursor.getString(columnNames));
            artist.setTracks(artistsCursor.getInt(columnTracks));
            artist.setAlbum(artistsCursor.getInt(columnAlbums));
            artist.setLink(artistsCursor.getString(columnLinks));
            artist.setDescription(artistsCursor.getString(columnDescriptions));
            artist.setSmallCoverUrl(artistsCursor.getString(columnSmallCovers));
            artist.setBigCoverUrl(artistsCursor.getString(columnBigCovers));

            Uri genresUri = ContentUris.withAppendedId(ProviderConstants.GENRES_URI, artist.getId());
            genresCursor = contentResolver.query(genresUri, null, null, null, null);
            String[] genres = new String[genresCursor.getCount()];
            genresCursor.moveToFirst();
            int i = 0;
            while (!genresCursor.isAfterLast()) {
                genres[i] = genresCursor.getString(0);
                genresCursor.moveToNext();
                i++;
            }
            artist.setGenres(genres);

            artists.add(artist);
            artistsCursor.moveToNext();
        }

        if (genresCursor != null) genresCursor.close();
        artistsCursor.close();
    }

}
