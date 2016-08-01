package com.nodomain.loader;

import android.app.Application;
import android.content.Context;

import com.nodomain.loader.di.components.AppComponent;
import com.nodomain.loader.di.components.DaggerAppComponent;
import com.nodomain.loader.di.modules.AppModule;

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
