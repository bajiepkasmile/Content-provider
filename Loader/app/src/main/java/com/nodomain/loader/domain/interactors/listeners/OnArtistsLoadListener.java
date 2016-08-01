package com.nodomain.loader.domain.interactors.listeners;

public interface OnArtistsLoadListener {

    void onArtistsSavingFinished();

    void onArtistsLoadingSuccess();

    void onArtistsLoadingFailure();

}
