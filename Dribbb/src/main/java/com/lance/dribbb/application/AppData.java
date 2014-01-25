package com.lance.dribbb.application;

import android.app.Application;
import android.content.Context;

public class AppData extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

}
