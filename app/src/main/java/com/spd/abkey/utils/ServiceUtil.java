package com.spd.abkey.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

/**
 * @author :xu in  2018/1/9 14:49.
 *         联系方式:QQ:2419646399
 *         功能描述:  判断是否快速点击以及判断扫描结果是否为utf8
 */
public class ServiceUtil {

    /**
     * 判断某个服务是否正在运行的方法
     * <p>
     * <p>
     * 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     *
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isWorked(Context context, String name) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        assert myManager != null;
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (name.equals(runningService.get(i).service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}



