package com.wanggang.commonlist.library;

public class BaseViewTypeEntity {

    private int layoutRes;
    private Class<? extends CommonViewHolder> viewHolder;

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public Class<? extends CommonViewHolder> getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(Class<? extends CommonViewHolder> viewHolder) {
        this.viewHolder = viewHolder;
    }
}
