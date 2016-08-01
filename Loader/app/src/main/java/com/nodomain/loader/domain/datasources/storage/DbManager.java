package com.nodomain.loader.domain.datasources.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nodomain.loader.model.Artist;

import java.util.ArrayList;

public class DbManager {

    private DbHelper dbHelper;

    public DbManager(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void saveArtists(ArrayList<Artist> artists) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(DbConstants.TABLE_ARTISTS, null, null);
        database.delete(DbConstants.TABLE_GENRES, null, null);

        ContentValues cvArtist = new ContentValues();
        ContentValues cvGenre = new ContentValues();

        for (Artist artist: artists) {
            cvArtist.put(DbConstants.COLUMN_IDS, artist.getId());
            cvArtist.put(DbConstants.COLUMN_NAMES, artist.getName());
            cvArtist.put(DbConstants.COLUMN_TRACKS, artist.getTracks());
            cvArtist.put(DbConstants.COLUMN_ALBUMS, artist.getAlbum());
            cvArtist.put(DbConstants.COLUMN_LINKS, artist.getLink());
            cvArtist.put(DbConstants.COLUMN_DESCRIPTIONS, artist.getDescription());
            cvArtist.put(DbConstants.COLUMN_SMALL_COVERS_URLS, artist.getSmallCoverUrl());
            cvArtist.put(DbConstants.COLUMN_BIG_COVERS_URLS, artist.getBigCoverUrl());
            database.insert(DbConstants.TABLE_ARTISTS, null, cvArtist);

            for (String genre: artist.getGenres()) {
                cvGenre.put(DbConstants.COLUMN_IDS, artist.getId());
                cvGenre.put(DbConstants.COLUMN_GENRES, genre);
                database.insert(DbConstants.TABLE_GENRES, null, cvGenre);
            }
        }

        dbHelper.close();
    }

}
