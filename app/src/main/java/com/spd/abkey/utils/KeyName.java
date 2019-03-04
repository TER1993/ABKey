package com.spd.abkey.utils;

import com.spd.abkey.AppAbKey;
import com.spd.abkey.R;

/**
 * 工具类
 *
 *  @author :zhang zhi chao  in  2019/2/21
 *  联系方式:QQ:981547125
 *  功能描述:将键值转化成按键名
 */
public class KeyName {

    public static String keyName;

    public static String getKeyName(int keyCode){
        switch (keyCode){
            case 0:
                keyName = AppAbKey.getInstance().getString(R.string.unknown_key);
                break;
            case 24:
                keyName = AppAbKey.getInstance().getString(R.string.volume_key_add);
                break;
            case 25:
                keyName = AppAbKey.getInstance().getString(R.string.volume_key_minus);
                break;
            case 134:
                keyName = AppAbKey.getInstance().getString(R.string.scan_key_right);
                break;
            case 135:
                keyName = AppAbKey.getInstance().getString(R.string.scan_key_left);
                break;
            default:
                keyName = ""+keyCode;
                break;
        }
        return keyName;
    }
}
