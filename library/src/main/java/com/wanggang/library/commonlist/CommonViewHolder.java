package com.wanggang.library.commonlist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mDataBinding;

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        try {
            // 若itemview不是databing布局，则会抛出异常
            mDataBinding = DataBindingUtil.bind(itemView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 绑定数据
     * 将adapter、entity、position 都设置给相应的view
     * */
    public void convert(CommonAdapter adapter, BaseViewTypeEntity entity, int position) {
        if (mDataBinding != null) {

            mDataBinding.setVariable(ViewHolderHelper.BR_ADAPTER, adapter);

            mDataBinding.setVariable(ViewHolderHelper.BR_ENTITY, entity);

            mDataBinding.setVariable(ViewHolderHelper.BR_POSITION, position);

        }
    }
}
