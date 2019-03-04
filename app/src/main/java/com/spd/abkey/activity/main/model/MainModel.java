package com.spd.abkey.activity.main.model;

import android.view.KeyEvent;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.activity.main.contract.MainContract;
import com.spd.abkey.base.BaseModel;

/**
 * @author xuyan
 */
public class MainModel extends BaseModel implements MainContract.Model {
    public static final String KEYCODE = "KEYCODE";
    public static final int KEYCODE_A = AppAbKey.getAppKeyA();
    public static final int KEYCODE_B = AppAbKey.getAppKeyB();
}
