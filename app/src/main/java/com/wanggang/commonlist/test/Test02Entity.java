package com.wanggang.commonlist.test;

import com.wanggang.commonlist.R;
import com.wanggang.commonlist.library.BaseViewTypeEntity;

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
        setLayoutRes(R.layout.holder_item_test02);
    }

}
