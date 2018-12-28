package com.spd.abkey;

import android.app.Application;

/**
 * @author xuyan
 */
public class AppAbKey extends Application {
    private static AppAbKey sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppAbKey getInstance() {
        return sInstance;
    }
}
