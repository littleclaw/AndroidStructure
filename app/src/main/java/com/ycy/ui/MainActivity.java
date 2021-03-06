package com.ycy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.ycy.baseapp.base.BaseActivity;
import com.ycy.ui.guide.GuideFragment;
import com.ycy.ui.main.MainPageFragment;
import com.ycy.ui.tasks.TaskFragment;
import com.ycy.ui.user.UserFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager mMainPageViewPager;
    private BottomNavigationView mMainPageBottomNavigationView;

    public static final int INDEX_MAIN = 0;
    public static final int INDEX_GUIDE = 1;
    public static final int INDEX_TASKS = 2;
    public static final int INDEX_USER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpPagerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initViews() {
        mMainPageViewPager = findViewById(R.id.main_viewpager);
        mMainPageBottomNavigationView = findViewById(R.id.main_bottom_navigation_view);
        mMainPageBottomNavigationView.inflateMenu(R.menu.nav_menu);
        mMainPageBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mMainPageBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
    }

    private void setUpPagerView(){
        ArrayList<Fragment> mainPageFragmentArrayList = new ArrayList<>();
        mainPageFragmentArrayList.add(new MainPageFragment());
        mainPageFragmentArrayList.add(new GuideFragment());
        mainPageFragmentArrayList.add(new TaskFragment());
        mainPageFragmentArrayList.add(new UserFragment());
        PagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mainPageFragmentArrayList.get(i);
            }

            @Override
            public int getCount() {
                return mainPageFragmentArrayList.size();
            }
        };

        mMainPageViewPager.setAdapter(pagerAdapter);
        setSelectedItem(INDEX_MAIN);
    }

    private void setSelectedItem(int index){
        mMainPageViewPager.setCurrentItem(index);
        mMainPageBottomNavigationView.getMenu().getItem(index).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_main:
                setSelectedItem(INDEX_MAIN);
                break;
            case R.id.item_guide:
                setSelectedItem(INDEX_GUIDE);
                break;
            case R.id.item_tasks:
                setSelectedItem(INDEX_TASKS);
                break;
            case R.id.item_user:
                setSelectedItem(INDEX_USER);
                break;
                default:
        }
        return false;
    }

}
