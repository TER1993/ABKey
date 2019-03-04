package com.spd.abkey.utils;

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
                keyName = "未知键";
                break;
            case 24:
                keyName = "音量键加";
                break;
            case 25:
                keyName = "音量键减";
                break;
            case 134:
                keyName = "扫描键右";
                break;
            case 135:
                keyName = "扫描键左";
                break;
            default:
                keyName = ""+keyCode;
                break;
        }
        return keyName;
    }
}
