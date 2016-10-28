package com.buybuybuy.ui.home;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Mr.J on 2016/10/13.
 */

public class HomeLBAdapter extends PagerAdapter {

    private List<ImageView> views;

    public HomeLBAdapter(List<ImageView> views){
        this.views=views;
    }

    @Override
    public int getCount() {
        return views.size();
    }
    //为了防止OOM
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }
}
