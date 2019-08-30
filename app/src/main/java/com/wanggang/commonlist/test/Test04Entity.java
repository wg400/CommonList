package com.wanggang.commonlist.test;

import com.wanggang.commonlist.CommonAdapterEnum;
import com.wanggang.library.commonlist.BaseViewTypeEntity;

public class Test04Entity extends BaseViewTypeEntity {

    private String title;
    private String text;
    private String hint;
    private boolean showLine;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isShowLine() {
        return showLine;
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
    }

    public Test04Entity(String title, String text, String hint, boolean showLine) {
        this.title = title;
        this.text = text;
        this.hint = hint;
        this.showLine = showLine;

        viewType = CommonAdapterEnum.TEST04.ordinal();
    }

    public Test04Entity() {
        viewType = CommonAdapterEnum.TEST04.ordinal();
    }

}
