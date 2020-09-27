package com.task.taskone_listofapps;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class MainClass {

    //Singleton class
    private static final MainClass singleTonInstance = new MainClass();


    //return instance
    public static MainClass getInstance() {
        return singleTonInstance;
    }


    //return list of installed apps
    public List<PackageInfo> getInstalledApps(Context context) {
        final PackageManager pm = context != null ? context.getPackageManager() : null;
        return pm.getInstalledPackages(0);
    }


}
