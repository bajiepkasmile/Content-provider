package com.nodomain.viewer;

import com.nodomain.viewer.di.components.AppComponent;
import com.nodomain.viewer.di.components.DaggerAppComponent;
import com.nodomain.viewer.di.modules.AppModule;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).component;
    }

}
