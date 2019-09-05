package com.spd.abkey;

import android.app.Application;

import com.spd.abkey.utils.SpUtils;

/**
 * @author xuyan
 */
public class AppAbKey extends Application {
    private static AppAbKey sInstance;
    /**
     * 按键值A
     */
    private static int AppKeyA;

    /**
     * 按键值B
     */
    private static int AppKeyB;

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
        SpUtils.put(sInstance, "appKeyA", appKeyA);
    }
    // 获取键值A,默认给F1
    public static int getAppKeyA(){
        AppKeyA = (int) SpUtils.get(sInstance, "appKeyA", 131);
        return AppKeyA;
    }
    // 存储键值B
    public static void setAppKeyB(int appKeyB){
        SpUtils.put(sInstance, "appKeyB", appKeyB);
    }
    // 获取键值B,默认给F2
    public static int getAppKeyB(){
        AppKeyB = (int) SpUtils.get(sInstance, "appKeyB", 132);
        return AppKeyB;
    }
}
