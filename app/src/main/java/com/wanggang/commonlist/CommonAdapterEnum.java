package com.wanggang.commonlist;

import com.wanggang.library.commonlist.CommonViewHolder;

public enum CommonAdapterEnum {

    /**
     * 所有item view 的清单
     * */
    TEST01(R.layout.holder_item_test01),
    TEST02(R.layout.holder_item_test02),
    TEST03(R.layout.holder_item_test03),
    PADDING12(R.layout.holder_padding12);

    public int layoutRes;

    public Class<? extends CommonViewHolder> viewHolderClass;

    CommonAdapterEnum(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    CommonAdapterEnum(int layoutRes, Class<? extends CommonViewHolder> viewHolderClass) {
        this.layoutRes = layoutRes;
        this.viewHolderClass = viewHolderClass;
    }
}
