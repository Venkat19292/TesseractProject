package com.task.assignment;

import android.graphics.drawable.Drawable;

public class AppListModel {

    String appname="";
    String packageName="";
    String versionname="";
    String versionCode="";

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getAppClassname() {
        return appClassname;
    }

    public void setAppClassname(String appClassname) {
        this.appClassname = appClassname;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    String appClassname="";
    Drawable appIcon;

}
