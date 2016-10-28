package com.buybuybuy.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeAdapter extends FragmentPagerAdapter {

    private static String[] titles = {"女生精选", "男生精选", "优惠卷", "9.9包邮", "19.9包邮", "29.9包邮", "今日上新"};
    private Fragment fragment;
    private Bundle bundle;


    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.NvSJX);
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.NanSJX);
                fragment.setArguments(bundle);
                break;
            case 2:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.YHJ);
                fragment.setArguments(bundle);
                break;
            case 3:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.JDJBY);
                fragment.setArguments(bundle);
                break;
            case 4:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.SJDJBY);
                fragment.setArguments(bundle);
                break;
            case 5:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.ESDJBY);
                fragment.setArguments(bundle);
                break;
            case 6:
                fragment = new HomeFragment();
                bundle = new Bundle();
                bundle.putString("pos", Constants.JRSX);
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
