package com.buybuybuy.ui.person.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.buybuybuy.R;
import com.buybuybuy.ui.person.DataCleanManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

//TODO:设置（当前版本v2.3.17）
public class FiveBottomActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_bottom_a,ll_bottom_b,ll_bottom_c,ll_bottom_d,ll_bottom_e,ll_bottom_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_bottom);
        //获取APP缓存文件的大小
        //long size = DataCleanManager.getFolderSize(file);
        //找到控件
        initView();
        //LinearLayout实现点击事件
        llBottomClick();
    }
    //LinearLayout实现点击事件
    private void llBottomClick() {
        ll_bottom_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FiveBottomActivity.this,"为我点赞",Toast.LENGTH_LONG).show();
                startActivity(new Intent(FiveBottomActivity.this, FiveBody14Activity.class));
            }
        });
        ll_bottom_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除本应用所有缓存
                DataCleanManager.cleanInternalCache(FiveBottomActivity.this);
                Toast.makeText(FiveBottomActivity.this,"清理完成",Toast.LENGTH_LONG).show();
            }
        });
        ll_bottom_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FiveBottomActivity.this,"当前是最新版本",Toast.LENGTH_LONG).show();
            }
        });
        ll_bottom_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FiveBottomActivity.this,"抱歉，该功能尚未开启，敬请期待",Toast.LENGTH_LONG).show();
            }
        });
        ll_bottom_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FiveBottomActivity.this,"推荐应用给好友",Toast.LENGTH_LONG).show();
                //分享应用
                sharedpreferences();
            }
        });
        ll_bottom_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FiveBottomActivity.this,"意见反馈",Toast.LENGTH_LONG).show();
                startActivity(new Intent(FiveBottomActivity.this, FiveBody11Activity.class));
            }
        });
    }

    //TODO:的三方分享############################################
    //回调监听
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(FiveBottomActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(FiveBottomActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(FiveBottomActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    //第三方分享
    private void sharedpreferences() {
        //设置分享的平台
        SHARE_MEDIA[] list = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE };
        new ShareAction(this)
                .setDisplayList(list)//设置分享的平台列表
                //分享的内容
                .withText("有一大波便宜包邮的好货正在出没，快加入购物车吧")
                //分享的标题
                .withTitle("购物的小车说满就满")
                //多媒体:图片,音频,视频,表情等
                .withMedia(new UMImage(FiveBottomActivity.this, R.drawable.ic_category_baoyou))
                //分享一个链接
                .withTargetUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.oppa.qz1yuan")
                .setCallback(umShareListener)//分享的监听
                .open();//打开分享面板
    }
    private void initView() {
        ll_bottom_a = (LinearLayout) findViewById(R.id.ll_bottom_a);
        ll_bottom_b = (LinearLayout) findViewById(R.id.ll_bottom_b);
        ll_bottom_c = (LinearLayout) findViewById(R.id.ll_bottom_c);
        ll_bottom_d = (LinearLayout) findViewById(R.id.ll_bottom_d);
        ll_bottom_e = (LinearLayout) findViewById(R.id.ll_bottom_e);
        ll_bottom_f = (LinearLayout) findViewById(R.id.ll_bottom_f);
    }

    @Override
    public void onClick(View v) {

    }
}


