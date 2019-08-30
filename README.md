#### 前言
在手机软件的设计中，充斥了大量的列表界面。而在我们通常的开发过程中，开发一个列表界面需要新建一个fragment或者activity作为界面载体，新建一个layout作为布局文件（内包含一个recyclerview控件），新建一个adapter和一个viewholder来绑定数据。至少需要新建这4个文件才能完成一个列表界面的开发工作。

本文旨在减少列表开发过程中一些不必要的重复的开发工作。

#### BaseViewTypeEntity

所有数据源单个实体对象的基类，其代码十分简单

```
public class BaseViewTypeEntity {

    public int viewType;

    public BaseViewTypeEntity() {
    }

    public BaseViewTypeEntity(int viewType) {
        this.viewType = viewType;
    }
}
```
其内部只有一个viewType成员变量，用来区分对应的itemView的类型。

#### CommonViewHolder

通用的ViewHolder

```
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
```
这里主要就是生成databinding对象，并且将数据设置给layout，剩下的事情就交给databingding来处理了。
这里给databingding设置了三个数据源（adapter、entity、position），那么对用的layout如下

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <import type="android.view.View"/>
        <variable
            name="adapter"
            type="com.wanggang.library.commonlist.CommonAdapter" />
        <variable
            name="entity"
            type="com.wanggang.commonlist.test.Test04Entity" />
        <variable
            name="position"
            type="Integer" />
    </data>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:background="@android:color/white">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="@{entity.title}"
            android:textSize="16sp"
            android:textColor="@android:color/black"
            android:paddingLeft="16dp"
            android:paddingRight="16dp"
            android:gravity="center"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:paddingRight="16dp"
            android:textSize="14sp"
            android:gravity="right|center"
            android:text="@{entity.text}"
            android:textColor="@android:color/black"
            android:hint="@{entity.hint}"
            android:drawablePadding="16dp"
            android:drawableRight="@drawable/icon_arrow_right"/>

        <View
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:visibility="@{entity.showLine ? View.VISIBLE : View.GONE}"
            android:layout_alignParentBottom="true"
            android:background="#bebebe"/>
    </RelativeLayout>
</layout>
```

#### CommonAdapter

最后来看一下adapter的通用写法

```
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

```

其中getItemViewType方法返回对用数据源BaseViewTypeEntity对象的viewtype；onCreateViewHolder方法根据viewType生成对应的CommonViewHolder；onBindViewHolder方法则直接调用CommonViewHolder的convert方法设置变量给layout。

我们在来看一下ViewHolderHelper的代码

```
public class ViewHolderHelper {

    private static Object[] viewHolderEnums;
    private static Field layoutField;

    public static Class enumClazz; //客户端layout和viewholder清单对应的class

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

        try {

            Object obj = viewHolderEnums[viewType];
            int layoutRes = layoutField.getInt(obj);
            return new CommonViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutRes, viewGroup, false));

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

```

这个其实就是使用java 的反射机制，根据viewType获取对应的枚举对象，然后通过枚举对象里面的布局文件layoutRes，生成CommonViewHolder。使用反射机制的主要原因是为了使以上代码与业务模块分离，业务模块只需要将对应的枚举类传递过来就行了。

以上就是我们打造通用Adapter的核心代码。接下来我们用他来写一个小demo。
1. 将以上的代码作为library引入到你的工程里面，当然也可以直接将代码直接拷贝到你的工程里。
2.  创建枚举文件CommonAdapterEnum

```
public enum CommonAdapterEnum {

    /**
     * 所有item view 的清单
     * */
    TEST01(R.layout.holder_item_test01),
    TEST02(R.layout.holder_item_test02),
    TEST03(R.layout.holder_item_test03),
    TEST04(R.layout.holder_item_test04),
    PADDING12(R.layout.holder_padding12);

    public int layoutRes;

    CommonAdapterEnum(int layoutRes) {
        this.layoutRes = layoutRes;
    }
}
```
这个是所有itemview的布局文件和viewType的清单文件，其中layoutRes对应布局文件，枚举的索引对应viewType。

3. 在Application的onCreate方法里调用以下方法

```
    ViewHolderHelper.BR_ADAPTER = BR.adapter;
    ViewHolderHelper.BR_ENTITY = BR.entity;
    ViewHolderHelper.BR_POSITION = BR.position;
    ViewHolderHelper.enumClazz = CommonAdapterEnum.class;
```
4.创建对应的数据模型，例如：

```
public class Test01Entity extends BaseViewTypeEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Test01Entity(String text) {
        this.text = text;
        viewType = CommonAdapterEnum.TEST01.ordinal();
    }

}
```
Test01Entity集成自BaseViewTypeEntity，并且他的viewType = CommonAdapterEnum.TEST01.ordinal()，那么他就是对应的R.layout.holder_item_test01布局文件。

5.创建包含RecyclerView的界面，并且设置CommonAdapter以及数据源。


```
public class MainActivity extends AppCompatActivity {

    CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonAdapter();
        recyclerView.setAdapter(mAdapter);

        List<BaseViewTypeEntity> list = new ArrayList<>();
        list.add(new Test02Entity("111111111"));

        Test03Entity test03Entity = new Test03Entity();
        test03Entity.setMenu1("模块1");
        test03Entity.setMenu2("模块2");
        test03Entity.setMenu3("模块3");
        test03Entity.setMenu4("模块4");
        list.add(test03Entity);

        list.add(new Test01Entity("111111111"));
        list.add(new Test01Entity("222222222"));
        list.add(new Test01Entity("333333333"));
        list.add(new Test01Entity("444444444"));
        list.add(new Test01Entity("555555555"));
        list.add(new Test01Entity("6666666666"));
        list.add(new Test01Entity("777777777"));
        list.add(new Test01Entity("888888888"));

        mAdapter.addSource(list);
        mAdapter.notifyDataSetChanged();
    }
}
```

#### 总结
与传统的写法相比，使用通用的Adapter开发有以下几个优点：
1. 整个项目只有一个Adapter和一个ViewHolder，减少了代码数量。
2. 所有的列表item的布局文件全部在一个枚举类里面列举出来了，所有的界面布局一目了然，方便代码的查找和复用。
3. 符合数据驱动界面显示。

#### 扩展
1. 如何打造通用的列表fragment。
