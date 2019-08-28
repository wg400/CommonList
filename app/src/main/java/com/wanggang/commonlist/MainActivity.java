package com.wanggang.commonlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.wanggang.commonlist.test.Test01Entity;
import com.wanggang.commonlist.test.Test02Entity;
import com.wanggang.commonlist.test.Test03Entity;
import com.wanggang.library.commonlist.BaseViewTypeEntity;
import com.wanggang.library.commonlist.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

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
        list.add(new Test01Entity("888888888"));
        list.add(new Test01Entity("888888888"));
        list.add(new Test01Entity("888888888"));
        list.add(new Test01Entity("888888888"));
        list.add(new Test01Entity("888888888"));

        mAdapter.addSource(list);
        mAdapter.notifyDataSetChanged();
    }
}
