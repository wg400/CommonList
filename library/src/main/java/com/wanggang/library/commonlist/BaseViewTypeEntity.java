package com.wanggang.library.commonlist;

/**
 * 所有用来做recyclerview item渲染的数据源的基类
 * viewType对应的adapter中的getItemViewType()
 * */
public class BaseViewTypeEntity {

    public int viewType;

    public BaseViewTypeEntity() {
    }

    public BaseViewTypeEntity(int viewType) {
        this.viewType = viewType;
    }
}
