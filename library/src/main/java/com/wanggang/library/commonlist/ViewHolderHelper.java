package com.wanggang.library.commonlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ViewHolderHelper {

    private static Object[] viewHolderEnums;
    private static Field layoutField;
    private static Field holderField;

    public static Class enumClazz; //客户端layout和viewholder清单对应的class

    /**
     * mvvm框架生成的BR常量
     * */
    public static int BR_ADAPTER = 0;
    public static int BR_ENTITY = 0;
    public static int BR_POSITION = 0;

    /**
     * 通过view type来获取对应的viewholder
     */
    public static CommonViewHolder getViewHolderByViewType(ViewGroup viewGroup, int viewType) {

        if (viewHolderEnums == null) {
            // 获取所有的枚举类型
            viewHolderEnums = enumClazz.getEnumConstants();
        }
        if (layoutField == null) {
            try {
                layoutField = enumClazz.getField("layoutRes");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (holderField == null) {
            try {
                holderField = enumClazz.getField("viewHolderClass");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        try {

            Object obj = viewHolderEnums[viewType];
            int layoutRes = layoutField.getInt(obj);

            if (holderField.get(obj) == null) {
                return new CommonViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutRes, viewGroup, false));
            } else {
                Class holderClazz = (Class) holderField.get(obj);
                Constructor<?>[] constructors = holderClazz.getConstructors();
                return (CommonViewHolder) constructors[0].newInstance(LayoutInflater.from(viewGroup.getContext()).inflate(layoutRes, viewGroup, false));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

}
