package com.wanggang.commonlist.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    /**
     * 数据源
     * */
    private List<BaseViewTypeEntity> dataSource;

    public CommonAdapter() {
        dataSource = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).getLayoutRes();
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

//        BaseViewTypeEntity entity = dataSource.get(position);
        Log.d("tag", position + "");

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(position, viewGroup, false);

//        if (entity.getViewHolder() != null) {
//            /**
//             * 通过java的反射机制构造对象
//             * */
//            Constructor<?>[] constructors = entity.getViewHolder().getConstructors();
//            try {
//                return (CommonViewHolder) constructors[0].newInstance(itemView);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } else {
            return new CommonViewHolder(itemView);
//        }

//        return null;
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
