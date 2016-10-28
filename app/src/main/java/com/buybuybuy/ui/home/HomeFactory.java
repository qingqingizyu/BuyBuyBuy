package com.buybuybuy.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Created by Mr.J on 2016/10/12.
 */

public class HomeFactory {
    private static SparseArray<Fragment> fragments = new SparseArray<>();
    private static final int ONE = 0;
    private static final int TWO = 1;
    private static final int THREE = 2;
    private static final int FOUR = 3;
    private static final int FIVE = 4;
    private static final int SEX = 5;
    private static final int SERVEN = 6;
    //创建Fragment的工厂方法
    static Fragment createFragment(int key, String msg) {
        Fragment fragment = fragments.get(key);
        if (fragment == null) {
            switch (key) {
                case ONE:
                    fragment = new HomeLB2Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("pos", Constants.NvSJX);
                    fragment.setArguments(bundle);
                    break;
                case TWO:
                    fragment = new MyHomeFragment();
                    bundle = new Bundle();
                    bundle.putString("pos", Constants.NanSJX);
                    fragment.setArguments(bundle);
                    break;
                case THREE:
                    fragment = new MyHomeFragment2();
                    bundle = new Bundle();
                    bundle.putString("home", Constants.YHJ);
                    fragment.setArguments(bundle);
                    break;
                case FOUR:
                    fragment = new MyHomeFragment();
                    bundle = new Bundle();
                    bundle.putString("pos", Constants.JDJBY);
                    fragment.setArguments(bundle);
                    break;
                case FIVE:
                    fragment = new MyHomeFragment();
                    bundle = new Bundle();
                    bundle.putString("pos", Constants.SJDJBY);
                    fragment.setArguments(bundle);
                    break;
                case SEX:
                    fragment = new MyHomeFragment();
                    bundle = new Bundle();
                    bundle.putString("pos", Constants.ESDJBY);
                    fragment.setArguments(bundle);
                    break;
                case SERVEN:
                    fragment = new MyHomeFragment2();
                    bundle = new Bundle();
                    bundle.putString("home", Constants.JRSX);
                    fragment.setArguments(bundle);
                    break;

            }

            fragments.put(key, fragment);
        }

        return fragment;
    }
}
