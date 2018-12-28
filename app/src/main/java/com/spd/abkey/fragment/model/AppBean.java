package com.spd.abkey.fragment.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xuyan
 */
public class AppBean implements Parcelable {
    /**
     * 应用名
      */
    public String appName ;
    /**
     * 包名
      */
    public String packageName ;
    /**
     * 版本名
      */
    public String versionName ;
    /**
     * 版本号
      */
    public int versionCode = 0;
    /**
     * 应用图标
      */
    public Drawable appIcon = null;
    /**
     * 是否是被选择的那个
     */
    public boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.packageName);
        dest.writeString(this.versionName);
        dest.writeInt(this.versionCode);
        dest.writeParcelable((Parcelable) this.appIcon, flags);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    public AppBean() {
    }

    protected AppBean(Parcel in) {
        this.appName = in.readString();
        this.packageName = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readInt();
        this.appIcon = in.readParcelable(Drawable.class.getClassLoader());
        this.checked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AppBean> CREATOR = new Parcelable.Creator<AppBean>() {
        @Override
        public AppBean createFromParcel(Parcel source) {
            return new AppBean(source);
        }

        @Override
        public AppBean[] newArray(int size) {
            return new AppBean[size];
        }
    };
}
