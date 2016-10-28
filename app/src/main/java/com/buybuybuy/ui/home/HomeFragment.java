package com.buybuybuy.ui.home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buybuybuy.R;
import com.buybuybuy.ui.hot.SearchActivity;

public class HomeFragment extends Fragment {

    private ViewPager mVpHome;

    private static HomeFragment homeFragment;
    private String[] navigations;
    private TabLayout mTabLayout;
    private ImageView homeBtnImg;


    public HomeFragment() {
        // Required empty public constructor
    }

    //创建Fragment对象
    public  static Fragment setFragment(){
        if (homeFragment==null)
            synchronized (HomeFragment.class){
                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                }
            }
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeBtnImg = (ImageView) view.findViewById(R.id.home_btn_img);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        navigations = getResources().getStringArray(R.array.navigations);
        mVpHome = (ViewPager) view.findViewById(R.id.vp_home);

        homeBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });

        //关联ViewPager的适配器
        mVpHome.setAdapter(new MyAdapter(getFragmentManager()));

        //TabLayout和ViewPager关联在一起
        mTabLayout.setupWithViewPager(mVpHome, true);
        //添加导航标签
        //mTabLayout.addTab(mTabLayout.newTab().setText());
        //设置指示器的颜色
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#C90000"));
        //设置指示器的高度
        mTabLayout.setSelectedTabIndicatorHeight(2);
        //设置文字颜色
        mTabLayout.setTabTextColors(Color.BLACK, Color.parseColor("#C90000"));
        //导航模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mVpHome) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
            }
        });
    }

    //ViewPager的适配器
    class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomeFactory.createFragment(position, navigations[position]);
        }

        @Override
        public int getCount() {
            return navigations.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return navigations[position];
        }
    }

}
