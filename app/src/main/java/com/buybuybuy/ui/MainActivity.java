package com.buybuybuy.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.buybuybuy.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private UMShareAPI mShareAPI;
    RadioGroup mRg;
    private Unbinder bind;
    private Fragment homeFragment;
    private Fragment hotFragemnt;
    private Fragment sortFragment;
    private Fragment salesFragmentll;
    private Fragment personFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        init();
        initDao();
        //友盟统计与分享
        youmengShare();
    }
    //友盟统计与分享
    private void youmengShare() {
        //可以更改默认的session启动时间，默认为30秒
        MobclickAgent.setSessionContinueMillis(10000);

        //日志加密.如果enable为true，SDK会对日志进行加密。加密模式可以有效防止网络攻击，提高数据安全性。
        //如果enable为false，SDK将按照非加密的方式来传输日志。
        //如果您没有设置加密模式，SDK的加密模式为false（不加密）。
        MobclickAgent.enableEncrypt(true);
        //关闭错误统计功能.默认开启
        //MobclickAgent.setCatchUncaughtExceptions(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
        //初始化
        mShareAPI = UMShareAPI.get(MainActivity.this);
    }
    //进行友盟的统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void initDao() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bt_home:
                        select(0);
                        break;
                    case R.id.bt_hot:
                        select(1);
                        break;
                    case R.id.bt_sort:
                        select(2);
                        break;
                    case R.id.bt_sales:
                        select(3);
                        break;
                    case R.id.bt_person:
                        select(4);
                        break;
                }
            }
        });
    }

    private void init() {
        //初始化第一个界面
        select(0);
        mRg = (RadioGroup) findViewById(R.id.main_sy);
    }

    private void select(int i) {
        FragmentManager manager = getSupportFragmentManager();//获得Fragment管理器
        FragmentTransaction ft = manager.beginTransaction();
        //开启一个事务
        hideFragment(ft);   //先隐藏 Fragment

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = FragmentFactory.setFragment(i);
                    ft.add(R.id.main_fl, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case 1:
                if (hotFragemnt == null) {
                    hotFragemnt = FragmentFactory.setFragment(i);
                    ft.add(R.id.main_fl, hotFragemnt);
                } else {
                    ft.show(hotFragemnt);
                }
                break;
            case 2:
                if (sortFragment == null) {
                    sortFragment = FragmentFactory.setFragment(i);
                    ft.add(R.id.main_fl, sortFragment);
                } else {
                    ft.show(sortFragment);
                }
                break;
            case 3:
                if (salesFragmentll == null) {
                    salesFragmentll = FragmentFactory.setFragment(i);
                    ft.add(R.id.main_fl, salesFragmentll);
                } else {
                    ft.show(salesFragmentll);
                }
                break;
            case 4:
                if (personFragment == null) {
                    personFragment = FragmentFactory.setFragment(i);
                    ft.add(R.id.main_fl, personFragment);
                } else {
                    ft.show(personFragment);
                }
                break;
        }
        ft.commit();   //提交事务
    }

    //隐藏所有Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (hotFragemnt != null) {
            fragmentTransaction.hide(hotFragemnt);
        }
        if (sortFragment != null) {
            fragmentTransaction.hide(sortFragment);
        }
        if (salesFragmentll != null) {
            fragmentTransaction.hide(salesFragmentll);
        }
        if (personFragment != null) {
            fragmentTransaction.hide(personFragment);
        }
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
