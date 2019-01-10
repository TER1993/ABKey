package com.spd.abkey.activity.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.R;
import com.spd.abkey.RobService;
import com.spd.abkey.activity.main.adapter.MainAdapter;
import com.spd.abkey.activity.main.contract.MainContract;
import com.spd.abkey.activity.main.presenter.MainPresenter;
import com.spd.abkey.base.BaseMvpActivity;
import com.spd.abkey.fragment.AkeyFragment;
import com.spd.abkey.fragment.BkeyFragment;
import com.spd.abkey.utils.Logcat;
import com.spd.abkey.utils.ToastUtils;
import com.spd.abkey.view.SlideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyan AB键启动
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, TabLayout.BaseOnTabSelectedListener {

    public SlideViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isAccessibilitySettingsOn(AppAbKey.getInstance())){
            ToastUtils.showLongToastSafe(R.string.key_barrier_free);
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }
    }



    // To check if service is enabled
    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + RobService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Logcat.d("accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Logcat.d( "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Logcat.d("***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Logcat.d( "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Logcat.d("We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Logcat.d("***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }


    @Override
    protected int getActLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mViewpager = findViewById(R.id.vp_container);
        TabLayout mTabMain = findViewById(R.id.tab_main);
        mTabMain.setupWithViewPager(mViewpager);
        List<Fragment> mTabList = new ArrayList<>();
        AkeyFragment akeyFragment = new AkeyFragment();
        BkeyFragment bkeyFragment = new BkeyFragment();
        mTabList.add(akeyFragment);
        mTabList.add(bkeyFragment);
        mTabMain.addOnTabSelectedListener(this);
        MainAdapter mMenuAdapter = new MainAdapter(getSupportFragmentManager(), mTabList);
        mViewpager.setAdapter(mMenuAdapter);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_F4) {
//            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_AKEY, "");
//            if (!"".equals(name)) {
//                runapp(name, AppAbKey.getInstance());
//            }
//        } else if (keyCode == KeyEvent.KEYCODE_F5) {
//            String name = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_BKEY, "");
//            if (!"".equals(name)) {
//                runapp(name, AppAbKey.getInstance());
//            }
//        }
        return super.onKeyDown(keyCode, event);
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
