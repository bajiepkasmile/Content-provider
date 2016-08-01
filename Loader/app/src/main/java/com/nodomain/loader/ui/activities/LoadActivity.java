package com.nodomain.loader.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.loader.App;
import com.nodomain.loader.R;
import com.nodomain.loader.domain.interactors.LoadArtistsInteractor;
import com.nodomain.loader.domain.interactors.listeners.OnArtistsLoadListener;
import com.nodomain.loader.utils.NetworkUtil;
import com.nodomain.loader.utils.ToastManager;

import javax.inject.Inject;

public class LoadActivity extends AppCompatActivity implements View.OnClickListener, OnArtistsLoadListener {

    @Inject
    NetworkUtil networkUtil;
    @Inject
    LoadArtistsInteractor updateInteractor;

    private View pbLoading;
    private TextView tvStatus;
    private View btnLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        App.getComponent(this).inject(this);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateInteractor.setListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateInteractor.removeListener();
    }

    @Override
    public void onClick(View view) {
        showProgress();
        loadArtistsList();
    }

    @Override
    public void onArtistsLoadingSuccess() {
        showSuccessMessage();
        tvStatus.setText("Сохранение данных...");
    }

    @Override
    public void onArtistsLoadingFailure() {
        hideProgress();
        showFailureMessage();
    }

    @Override
    public void onArtistsSavingFinished() {
        hideProgress();
        showFinishMessage();
    }

    private void initViews() {
        pbLoading = findViewById(R.id.pb_loading);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        btnLoad = findViewById(R.id.btn_load);

        btnLoad.setOnClickListener(this);
    }

    private void loadArtistsList() {
        if (networkUtil.isNetworkAvailable()) {
            updateInteractor.execute();
            showProgress();
        } else {
            showNoNetworkMessage();
        }
    }

    private void showProgress() {
        tvStatus.setText("Загрузка данных...");
        pbLoading.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.VISIBLE);
        btnLoad.setVisibility(View.GONE);
    }

    private void hideProgress() {
        pbLoading.setVisibility(View.GONE);
        tvStatus.setVisibility(View.GONE);
        btnLoad.setVisibility(View.VISIBLE);
    }

    public void showSuccessMessage() {
        ToastManager.showToast(this, R.string.message_data_loaded, Toast.LENGTH_SHORT);
    }

    public void showFailureMessage() {
        ToastManager.showToast(this, R.string.message_loading_failure, Toast.LENGTH_SHORT);
    }

    public void showFinishMessage() {
        ToastManager.showToast(this, R.string.message_data_saved, Toast.LENGTH_LONG);
    }

    private void showNoNetworkMessage() {
        ToastManager.showToast(this, R.string.message_no_network, Toast.LENGTH_SHORT);
    }

}
