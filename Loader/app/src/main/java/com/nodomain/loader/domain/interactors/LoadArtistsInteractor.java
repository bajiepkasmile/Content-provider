package com.nodomain.loader.domain.interactors;

import android.os.AsyncTask;

import com.nodomain.loader.domain.DataManager;
import com.nodomain.loader.domain.interactors.listeners.OnArtistsLoadListener;

import java.io.IOException;

public class LoadArtistsInteractor
        extends BaseInteractor<OnArtistsLoadListener, LoadArtistsInteractor.UpdateTask> {

    private DataManager dataManager;

    public LoadArtistsInteractor(DataManager dataManager) {
        this.dataManager = dataManager;
        task = new UpdateTask();
    }

    @Override
    public void execute() {
        if (task.getStatus() != AsyncTask.Status.RUNNING) {
            task = new UpdateTask();
            task.execute();
        }
    }

    class UpdateTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return dataManager.updateArtists();
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                if (listener != null) listener.onArtistsLoadingSuccess();
                new SaveTask().execute();
            } else {
                if (listener != null) listener.onArtistsLoadingFailure();
            }
        }

    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            dataManager.saveArtists();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (listener != null) {
                listener.onArtistsSavingFinished();
            }
        }

    }

}
