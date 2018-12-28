package com.spd.abkey.activity.main;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.activity.main.model.MainModel;
import com.spd.abkey.fragment.model.KeyModel;
import com.spd.abkey.utils.Logcat;
import com.spd.abkey.utils.SpUtils;

import java.util.List;

/**
 * @author :Reginer in  2018/1/25 11:09.
 * 联系方式:QQ:282921012
 * 功能描述:扫描的接收广播
 */
public class MainReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logcat.d("接收到的广播" + intent.getAction());

        int code = intent.getIntExtra(MainModel.KEYCODE, -1);

        if (code == MainModel.KEYCODE_A) {
            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_AKEY, "");
            if (!"".equals(name)) {
                runapp(name, AppAbKey.getInstance());
            }
        } else if (code == MainModel.KEYCODE_B) {
            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_BKEY, "");
            if (!"".equals(name)) {
                runapp(name, AppAbKey.getInstance());
            }
        }
    }

    public static void runapp(String packageName, Context mContext) {
        PackageInfo pi;
        try {
            pi = mContext.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pManager = mContext.getPackageManager();
            List apps = pManager.queryIntentActivities(
                    resolveIntent, 0);
            ResolveInfo ri = (ResolveInfo) apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
