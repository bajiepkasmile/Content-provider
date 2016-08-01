package com.nodomain.viewer.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nodomain.viewer.App;
import com.nodomain.viewer.R;
import com.nodomain.viewer.domain.DataManager;
import com.nodomain.viewer.model.Artist;
import com.nodomain.viewer.utils.AppConstants;
import com.nodomain.viewer.utils.Converter;

import javax.inject.Inject;

public class ArtistDetailsActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_details);

        App.getComponent(this).inject(this);

        int position = getIntent().getIntExtra(AppConstants.ARG_POSITION, 0);
        Artist artist = dataManager.getArtists().get(position);

        showDetails(artist);
    }

    private void showDetails(Artist artist) {
        TextView tvGenres = (TextView) findViewById(R.id.tv_genres);
        TextView tvStatistics = (TextView) findViewById(R.id.tv_statistics);
        TextView tvLink = (TextView) findViewById(R.id.tv_link);
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);

        if (artist.getGenres().length != 0) {
            tvGenres.setText(Converter.genresToString(artist.getGenres()));
        } else {
            tvGenres.setVisibility(View.GONE);
        }

        if (artist.getLink() != null) {
            tvLink.setText(artist.getLink());
        } else {
            tvLink.setVisibility(View.GONE);
        }

        tvStatistics.setText(
                Converter.albumsToString(artist.getAlbum()) +
                        "   •   " +
                        Converter.tracksToString(artist.getTracks()));
        tvDescription.setText(artist.getDescription());
    }

}
