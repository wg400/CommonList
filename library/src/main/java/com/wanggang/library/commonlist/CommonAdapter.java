package com.wanggang.library.commonlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的adapter
 *
 * */
public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    /**
     * 数据源
     */
    private List<BaseViewTypeEntity> dataSource;

    public CommonAdapter() {
        dataSource = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).viewType;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return ViewHolderHelper.getViewHolderByViewType(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder commonViewHolder, int i) {
        commonViewHolder.convert(this, dataSource.get(i), i);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void addSource(List<BaseViewTypeEntity> dataList) {
        dataSource.addAll(dataList);
    }

    public void addSource(BaseViewTypeEntity data) {
        dataSource.add(data);
    }

    public void clear() {
        dataSource.clear();
    }
}
