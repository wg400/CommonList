package com.wanggang.commonlist;

import android.app.Application;

import com.wanggang.commonlist.library.Constant;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Constant.BR_ADAPTER = BR.adapter;
        Constant.BR_ENTITY = BR.entity;
        Constant.BR_POSITION = BR.position;
    }
}
