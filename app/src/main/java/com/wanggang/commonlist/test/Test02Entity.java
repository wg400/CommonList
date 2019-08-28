package com.wanggang.commonlist.test;

import com.wanggang.commonlist.CommonAdapterEnum;
import com.wanggang.library.commonlist.BaseViewTypeEntity;

public class Test02Entity extends BaseViewTypeEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Test02Entity(String text) {
        this.text = text;
        viewType = CommonAdapterEnum.TEST02.ordinal();
    }

}
