package com.spd.abkey.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;

import com.spd.abkey.fragment.model.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用信息获取类
 *
 * @author 野虎
 * @时间 2016年2月23日下午3:47:13
 */
public class AppInfoUtil {
    /**
     * 默认 所有应用
     */
    public static final int DEFAULT = 0;
    /**
     * 系统应用
     */
    public static final int SYSTEM_APP = DEFAULT + 1;
    /**
     * 非系统应用
     */
    public static final int NONSYSTEM_APP = DEFAULT + 2;

    /**
     * 根据包名获取相应的应用信息
     *
     * @param context
     * @param packageName
     * @return 返回包名所对应的应用程序的名称。
     */
    public static String getProgramNameByPackageName(Context context,
                                                     String packageName) {
        PackageManager pm = context.getPackageManager();
        String name = null;
        try {
            name = pm.getApplicationLabel(
                    pm.getApplicationInfo(packageName,
                            PackageManager.GET_META_DATA)).toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取手机所有应用信息
     *
     * @param context
     */
    public static void getAllProgramInfo(List<AppBean> allApplist,
                                         Context context) {
        getAllProgramInfo(allApplist, context, DEFAULT);
    }

    /**
     * 获取手机所有应用信息
     *
     * @param context
     */
    public static void getSysProgramInfo(List<AppBean> allApplist,
                                         Context context) {
        getAllProgramInfo(allApplist, context, SYSTEM_APP);
    }


    /**
     * 获取手机所有应用信息
     *
     * @param context
     */
    public static void getNoSysProgramInfo(List<AppBean> allApplist,
                                           Context context) {
        getAllProgramInfo(allApplist, context, NONSYSTEM_APP);
    }


    /**
     * 获取手机所有应用信息
     *
     * @param applist
     * @param context
     * @param type    标识符 是否区分系统和非系统应用
     */
    public static void getAllProgramInfo(List<AppBean> applist,
                                         Context context, int type) {

        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> installAppList = context.getPackageManager().queryIntentActivities(mIntent, 0);

        for (int j = 0; j < installAppList.size(); j++) {
            ResolveInfo resolveInfo = installAppList.get(j);
            AppBean tmpInfo = new AppBean();
            tmpInfo.appName = resolveInfo.loadLabel(context.getPackageManager()).toString();
            tmpInfo.packageName = resolveInfo.activityInfo.packageName;
            tmpInfo.appIcon = resolveInfo.activityInfo.loadIcon(context.getPackageManager());
            applist.add(tmpInfo);
        }

    }

    /**
     * 获取所有系统应用信息
     *
     * @param context
     * @return
     */
    public static List<AppBean> getAllSystemProgramInfo(Context context) {
        List<AppBean> systemAppList = new ArrayList<AppBean>();
        getAllProgramInfo(systemAppList, context, SYSTEM_APP);
        return systemAppList;
    }

    /**
     * 获取所有非系统应用信息
     *
     * @param context
     * @return
     */
    public static List<AppBean> getAllNonsystemProgramInfo(Context context) {
        List<AppBean> nonsystemAppList = new ArrayList<AppBean>();
        getAllProgramInfo(nonsystemAppList, context, NONSYSTEM_APP);
        return nonsystemAppList;
    }

    /**
     * 判断是否是系统应用
     *
     * @param packageInfo
     * @return
     */
    public static Boolean isSystemAPP(PackageInfo packageInfo) {
        if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 非系统应用
            return false;
        } else { // 系统应用
            return true;
        }
    }
}
