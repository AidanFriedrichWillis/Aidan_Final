package com.example.finalproject.Application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public final class App extends Application {


    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }



}
