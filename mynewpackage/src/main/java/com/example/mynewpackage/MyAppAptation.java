package com.example.mynewpackage;

import android.app.Application;
import android.content.Context;

public class MyAppAptation extends Application {

    private static Context gContext;

    @Override
    public void onCreate() {
        super.onCreate();
        gContext = getApplicationContext();


    }


    public static Context getgContext() {
        return gContext;
    }
}
