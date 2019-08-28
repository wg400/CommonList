package com.wanggang.commonlist.test;

import com.wanggang.commonlist.R;
import com.wanggang.commonlist.library.BaseViewTypeEntity;

public class Test03Entity extends BaseViewTypeEntity {

    private String menu1;
    private String menu2;
    private String menu3;
    private String menu4;

    public String getMenu1() {
        return menu1;
    }

    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }

    public String getMenu3() {
        return menu3;
    }

    public void setMenu3(String menu3) {
        this.menu3 = menu3;
    }

    public String getMenu4() {
        return menu4;
    }

    public void setMenu4(String menu4) {
        this.menu4 = menu4;
    }

    public Test03Entity() {
        setLayoutRes(R.layout.holder_item_test03);
    }

}
