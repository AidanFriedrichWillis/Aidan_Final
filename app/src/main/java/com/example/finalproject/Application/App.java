package com.example.finalproject.Application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public final class App extends Application {


    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     *When the class is created it saves the appContext within this class.
     */
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    /**
     * A static method allowing the object not having to be Initialised.
     * @return The context of the application.
     */
    public static Context getAppContext() {
        return App.context;
    }



}
