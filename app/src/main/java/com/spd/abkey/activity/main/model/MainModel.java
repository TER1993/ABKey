package com.spd.abkey.activity.main.model;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.activity.main.contract.MainContract;
import com.spd.abkey.base.BaseModel;

/**
 * @author xuyan
 */
public class MainModel extends BaseModel implements MainContract.Model {
    public static final String KEYCODE = "KEYCODE";
    public int KEYCODE_A = AppAbKey.getAppKeyA();
    public int KEYCODE_B = AppAbKey.getAppKeyB();
}
