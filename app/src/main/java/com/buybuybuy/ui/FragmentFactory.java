package com.buybuybuy.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.buybuybuy.ui.home.HomeFragment;
import com.buybuybuy.ui.hot.HotFragment;
import com.buybuybuy.ui.person.PersonFragment;

import com.buybuybuy.ui.shop.ShopFragment;
import com.buybuybuy.ui.sort.SortFragment;

public class FragmentFactory {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_HOT = 1;
    private static final int FRAGMENT_SORT = 2;
    private static final int FRAGMENT_SHOP = 3;
    private static final int FRAGMENT_PERSON = 4;
    private static SparseArray<Fragment> list = new SparseArray<>();

    public static Fragment setFragment(int key) {
        Fragment fragment = list.get(key);
        if (fragment == null) {
            switch (key) {
                case FRAGMENT_HOME:
                    fragment = HomeFragment.setFragment();
                    break;
                case FRAGMENT_HOT:
                    fragment = HotFragment.setFragment();
                    break;
                case FRAGMENT_SORT:
                    fragment = SortFragment.setFragment();
                    break;
                case FRAGMENT_SHOP:
                    fragment = ShopFragment.setFragment();
                    break;
                case FRAGMENT_PERSON:
                    fragment = PersonFragment.setFragment();
                    break;
            }
            list.put(key,fragment);
        }
        return fragment;
    }
}
