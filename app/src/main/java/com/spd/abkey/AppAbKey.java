package com.spd.abkey;

import android.app.Application;
import android.view.KeyEvent;

/**
 * @author xuyan
 */
public class AppAbKey extends Application {
    private static AppAbKey sInstance;
    private static int AppKeyA;//按键值A
    private static int AppKeyB;//按键值B

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppAbKey getInstance() {
        return sInstance;
    }
    // 存储键值A
    public static void setAppKeyA(int appKeyA){
        AppKeyA = appKeyA;
    }
    // 获取键值A
    public static int getAppKeyA(){
        return AppKeyA;
    }
    // 存储键值B
    public static void setAppKeyB(int appKeyB){
        AppKeyB = appKeyB;
    }
    // 获取键值B
    public static int getAppKeyB(){
        return AppKeyB;
    }
}
