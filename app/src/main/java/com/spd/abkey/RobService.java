package com.spd.abkey;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemProperties;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.spd.abkey.fragment.model.KeyModel;
import com.spd.abkey.utils.SpUtils;

import java.util.List;

import static com.spd.abkey.fragment.model.KeyModel.SCAN_KEY;
import static com.spd.abkey.fragment.model.KeyModel.SCAN_SET;

/**
 * @author xuyan  无障碍监听
 */
public class RobService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == AppAbKey.getAppKeyA() && event.getAction() == KeyEvent.ACTION_DOWN) {
            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_AKEY, "");
            if (!"".equals(name)) {
                runapp(name, AppAbKey.getInstance(), "f1");
            }
        } else if (keyCode == AppAbKey.getAppKeyB() && event.getAction() == KeyEvent.ACTION_DOWN) {
            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_BKEY, "");
            if (!"".equals(name)) {
                runapp(name, AppAbKey.getInstance(), "f2");
            }
        }
        return super.onKeyEvent(event);
    }


    public static void runapp(String packageName, Context mContext, String f) {

        if (SCAN_SET.equals(packageName)) {
            SystemProperties.set(SCAN_KEY, f);
            return;
        }

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
