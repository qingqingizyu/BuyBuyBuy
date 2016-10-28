package com.buybuybuy.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.buybuybuy.R;


public class Welcome {
    private static Context contexts;
    private static int[] image;

    public Welcome(Context context) {
        contexts = context.getApplicationContext();
    }

    public static void showWelcome() {
        View inflate = LayoutInflater.from(contexts).inflate(R.layout.welcome, null);
        ViewPager mVp = (ViewPager) inflate.findViewById(R.id.welcome_vp);
        image = getImage();
        mVp.setAdapter(new MyAdapter());
    }
    private static int[] getImage(){
        return new int[]{R.drawable.bg_welcome};
    }
    private static class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return image!=null?image.length:0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
