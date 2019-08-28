package com.wanggang.commonlist;

import android.app.Application;

import com.wanggang.library.commonlist.Constant;
import com.wanggang.library.commonlist.ViewHolderHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Constant.BR_ADAPTER = BR.adapter;
        Constant.BR_ENTITY = BR.entity;
        Constant.BR_POSITION = BR.position;
        ViewHolderHelper.enumClazz = CommonAdapterEnum.class;
    }
}
