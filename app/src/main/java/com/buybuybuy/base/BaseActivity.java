package com.buybuybuy.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/* *
   * @author: ZTC
   * @Description: 基础activity
   * @data:  2016/10/11  11:09
   * @version: V1.0
   */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //去标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());
        //绑定黄油刀
        bind = ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    public abstract int getContentView();

    @Override
    protected void onDestroy() {
        //解绑
        bind.unbind();
        super.onDestroy();
    }
}
