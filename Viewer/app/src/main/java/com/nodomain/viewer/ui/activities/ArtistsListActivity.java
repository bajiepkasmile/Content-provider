package com.nodomain.viewer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nodomain.viewer.App;
import com.nodomain.viewer.R;
import com.nodomain.viewer.domain.DataManager;
import com.nodomain.viewer.domain.interactors.LoadArtistsInteractor;
import com.nodomain.viewer.domain.interactors.listeners.OnArtistsLoadListener;
import com.nodomain.viewer.ui.adapters.ArtistsListAdapter;
import com.nodomain.viewer.ui.interfaces.OnItemClickListener;
import com.nodomain.viewer.utils.AppConstants;
import com.nodomain.viewer.utils.ToastManager;

import javax.inject.Inject;

public class ArtistsListActivity extends AppCompatActivity
        implements View.OnClickListener, OnItemClickListener, OnArtistsLoadListener {

    @Inject
    DataManager dataManager;
    @Inject
    LoadArtistsInteractor extractInteractor;

    private View pbLoading;
    private View tvStatus;
    private View btnShow;
    private RecyclerView rvArtists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.artists_list);
        initViews();

        App.getComponent(this).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        extractInteractor.setListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        extractInteractor.removeListener();
    }

    @Override
    public void onClick(View view) {
        extractInteractor.execute();
        showProgress();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ArtistDetailsActivity.class);
        intent.putExtra(AppConstants.ARG_POSITION, position);
        startActivity(intent);
    }

    @Override
    public void onArtistsLoadingFinished() {
        hideProgress();
        showFinishMessage();
        showArtistsList();
    }

    @Override
    public void onArtistsLoadingFailure() {
        hideProgress();
        showFailureMessage();
    }

    private void initViews() {
        pbLoading = findViewById(R.id.pb_loading);
        tvStatus = findViewById(R.id.tv_status);
        btnShow = findViewById(R.id.btn_show);
        rvArtists = (RecyclerView) findViewById(R.id.rv_artists);

        btnShow.setOnClickListener(this);
    }

    private void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.VISIBLE);
        btnShow.setVisibility(View.GONE);
    }

    private void hideProgress() {
        pbLoading.setVisibility(View.GONE);
        tvStatus.setVisibility(View.GONE);
        btnShow.setVisibility(View.VISIBLE);
    }

    private void showFinishMessage() {
        ToastManager.showToast(this, R.string.message_loading_finished, Toast.LENGTH_SHORT);
    }

    private void showFailureMessage() {
        ToastManager.showToast(this, R.string.message_loading_failure, Toast.LENGTH_SHORT);
    }

    private void showArtistsList() {
        btnShow.setVisibility(View.GONE);
        rvArtists.setVisibility(View.VISIBLE);

        ArtistsListAdapter adapter = new ArtistsListAdapter(dataManager.getArtists(), this);
        rvArtists.setAdapter(adapter);
    }

}
