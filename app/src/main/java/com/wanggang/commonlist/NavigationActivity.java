package com.wanggang.commonlist;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity implements NavigationFragment01.OnFragmentInteractionListener {

    NavigationFragment01 fragment01;
    NavigationFragment01 fragment02;
    NavigationFragment01 fragment03;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showTab(0);
                    return true;
                case R.id.navigation_dashboard:
                    showTab(1);
                    return true;
                case R.id.navigation_notifications:
                    showTab(2);
                    return true;
            }
            return false;
        }
    };

    private void showTab(int position) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (position == 0) {
            // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
            hideFragments(transaction);
            if (fragment01 == null) {
                fragment01 = NavigationFragment01.newInstance("01");
                transaction.add(R.id.contentLayout, fragment01);
            } else {
                transaction.show(fragment01);
            }
        } else if (position == 1) {
            hideFragments(transaction);
            if (fragment02 == null) {
                fragment02 = NavigationFragment01.newInstance("02");
                transaction.add(R.id.contentLayout, fragment02);
            } else {
                transaction.show(fragment02);
            }
        } else if (position == 2) {
            hideFragments(transaction);
            if (fragment03 == null) {
                fragment03 = NavigationFragment01.newInstance("03");
                transaction.add(R.id.contentLayout, fragment03);
            } else {
                transaction.show(fragment03);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragment01 != null)
            transaction.hide(fragment01);
        if (fragment02 != null)
            transaction.hide(fragment02);
        if (fragment03 != null)
            transaction.hide(fragment03);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showTab(0);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
