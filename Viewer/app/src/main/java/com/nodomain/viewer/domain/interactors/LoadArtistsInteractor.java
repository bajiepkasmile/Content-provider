package com.nodomain.viewer.domain.interactors;

import android.os.AsyncTask;

import com.nodomain.viewer.domain.DataManager;
import com.nodomain.viewer.domain.interactors.listeners.OnArtistsLoadListener;

public class LoadArtistsInteractor extends BaseInteractor<OnArtistsLoadListener, LoadArtistsInteractor.LoadTask> {

    private DataManager dataManager;

    public LoadArtistsInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
        task = new LoadTask();
    }

    @Override
    public void execute() {
        if (!isRunning()) {
            task = new LoadTask();
            task.execute();
        }
    }

    class LoadTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                dataManager.loadArtists();
                return true;
            } catch (NullPointerException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                listener.onArtistsLoadingFinished();
            } else {
                listener.onArtistsLoadingFailure();
            }
        }

    }

}
