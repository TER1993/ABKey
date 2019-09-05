package com.spd.abkey.activity.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import com.spd.abkey.utils.KeyName;
import com.spd.abkey.utils.Logcat;
import com.spd.abkey.utils.ToastUtils;
import com.spd.abkey.view.SlideViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xuyan AB键启动
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View, TabLayout.BaseOnTabSelectedListener {

    public SlideViewPager mViewpager;
    private TabLayout mTabMain;
    private int keyPos;
    private int keyA;
    private int keyB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isAccessibilitySettingsOn(AppAbKey.getInstance())) {
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
            Logcat.d("Error finding setting, default accessibility to not found: "
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

                    Logcat.d("-------------- > accessibilityService :: " + accessibilityService + " " + service);
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
        mTabMain = findViewById(R.id.tab_main);
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
        // 获取位置
        keyPos = tab.getPosition();
        if (keyPos == 0) {
            ToastUtils.showLongToastSafe(R.string.Pleasressthe);
        } else if (keyPos == 1) {
            ToastUtils.showLongToastSafe(R.string.Pleareshe);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private int keyWord;
    /**
     * 返回键监听
     */
    private long mkeyTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //先排除返回键
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.ACTION_DOWN:
                if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                    mkeyTime = System.currentTimeMillis();
                    boolean cn = "CN".equals(getApplicationContext().getResources().getConfiguration().locale.getCountry());
                    if (cn) {
                        ToastUtils.showShortToastSafe("再次点击返回退出");
                    } else {
                        ToastUtils.showShortToastSafe("Press the exit again");
                    }
                } else {
                    try {
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            default:
                break;
        }

        keyWord = keyCode;
        if (keyPos == 0) {
            keyA = keyCode;
            ToastUtils.showLongToastSafe(getString(R.string.SetheA) + keyA);
            showNormalDialog();
        } else if (keyPos == 1) {
            keyB = keyCode;
            ToastUtils.showLongToastSafe(getString(R.string.SetheB) + keyB);
            showNormalDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        if (keyPos == 0){
            normalDialog.setTitle(getString(R.string.thecurrentset) + AppAbKey.getAppKeyA());
        }else if (keyPos == 1){
            normalDialog.setTitle(getString(R.string.thecurrentset) + AppAbKey.getAppKeyB());
        }
        normalDialog.setMessage(getString(R.string.Thcurrennew) + keyWord + "\n" + getString(R.string.areyousure));
        normalDialog.setPositiveButton(getString(R.string.sure),
                (dialog, which) -> {
                    //...To-do
                    if (keyPos == 0) {
                        AppAbKey.setAppKeyA(keyA);
                        Objects.requireNonNull(mTabMain.getTabAt(0)).setText(KeyName.getKeyName(keyA));
                    } else if (keyPos == 1) {
                        AppAbKey.setAppKeyB(keyB);
                        Objects.requireNonNull(mTabMain.getTabAt(1)).setText(KeyName.getKeyName(keyB));
                    }
                });
        normalDialog.setNegativeButton(getString(R.string.close),
                (dialog, which) -> {
                    //...To-do
                });
        // 显示
        normalDialog.show();
    }

}
