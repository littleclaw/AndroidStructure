package com.ycy.baseapp.base;

import android.app.Application;

import com.ycy.utils.CrashUtils;

public class YcyApplication extends Application {
    private static YcyApplication ycyApplication;

    public static YcyApplication getInstance(){
        return ycyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ycyApplication = this;
        CrashUtils.getInstance().init();
    }

}
