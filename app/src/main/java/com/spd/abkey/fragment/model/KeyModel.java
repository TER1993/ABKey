package com.spd.abkey.fragment.model;

import com.spd.abkey.base.BaseModel;
import com.spd.abkey.fragment.contract.KeyContract;

/**
 * @author xuyan
 */
public class KeyModel extends BaseModel implements KeyContract.Model {
    public static final String KAY_AKEY = "KAY_AKEY";
    public static final String KAY_BKEY = "KAY_BKEY";

    public static final String SCAN_HEAD = "persist.sys.scanheadtype";
    public static final String SCAN_KEY = "persist.sys.scankey";
    public static final String SCAN_SET = "com.speedata.scanset";

}
