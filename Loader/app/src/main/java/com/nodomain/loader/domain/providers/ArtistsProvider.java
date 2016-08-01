package com.nodomain.loader.domain.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.nodomain.loader.App;
import com.nodomain.loader.domain.datasources.storage.DbConstants;
import com.nodomain.loader.domain.datasources.storage.DbHelper;

import javax.inject.Inject;

public class ArtistsProvider extends ContentProvider {

    @Inject
    DbHelper dbHelper;

    private static UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ProviderConstants.AUTHORITY, ProviderConstants.PATH_ARTISTS, ProviderConstants.ARTISTS);
        uriMatcher.addURI(ProviderConstants.AUTHORITY, ProviderConstants.PATH_GENRES + "/#", ProviderConstants.GENRES);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        App.getComponent(getContext()).inject(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case ProviderConstants.ARTISTS:
                cursor = database.query(DbConstants.TABLE_ARTISTS, null, null, null, null, null, null);
                break;
            case ProviderConstants.GENRES:
                String id = uri.getLastPathSegment();
                cursor = database.query(DbConstants.TABLE_GENRES,
                        new String[]{DbConstants.COLUMN_GENRES},
                        DbConstants.COLUMN_IDS + " = " + id,
                        null,
                        null,
                        null,
                        null);;
                break;
            default:
                throw new IllegalArgumentException("Неправильынй URI: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

}
