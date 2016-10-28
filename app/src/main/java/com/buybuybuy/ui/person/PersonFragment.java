package com.buybuybuy.ui.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.buybuybuy.R;
import com.buybuybuy.ui.person.activity.FiveBody10Activity;
import com.buybuybuy.ui.person.activity.FiveBody11Activity;
import com.buybuybuy.ui.person.activity.FiveBody12Activity;
import com.buybuybuy.ui.person.activity.FiveBody14Activity;
import com.buybuybuy.ui.person.activity.FiveBody1Activity;
import com.buybuybuy.ui.person.activity.FiveBody2Activity;
import com.buybuybuy.ui.person.activity.FiveBody3Activity;
import com.buybuybuy.ui.person.activity.FiveBody5Activity;
import com.buybuybuy.ui.person.activity.FiveBody6Activity;
import com.buybuybuy.ui.person.activity.FiveBody7Activity;
import com.buybuybuy.ui.person.activity.FiveBody8Activity;
import com.buybuybuy.ui.person.activity.FiveBody9Activity;
import com.buybuybuy.ui.person.activity.FiveBottomActivity;
import com.buybuybuy.ui.person.activity.FiveLoginActivity;
import com.buybuybuy.ui.person.activity.FiveRegisterActivity;
import com.buybuybuy.ui.person.activity.FiveTop1Activity;
import com.buybuybuy.ui.person.activity.FiveTop2Activity;
import com.buybuybuy.ui.person.activity.FiveTop3Activity;
import com.buybuybuy.ui.person.activity.FiveTop4Activity;
import com.buybuybuy.ui.person.activity.FiveTopActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonFragment extends Fragment implements View.OnClickListener {

    //定义一个集合存放图片和文字
    private List<Map<String, Object>> datas;
    private RadioGroup rgPerson;
    private GridView gv;
    private LinearLayout ll_top,ll_bottom;
    private Button bt_login,bt_register;

    public PersonFragment() {
        // Required empty public constructor
    }
    private static PersonFragment cartFragment;
    public  static Fragment setFragment(){
        if (cartFragment==null)
            synchronized (PersonFragment.class){
                if (cartFragment==null){
                    cartFragment=new PersonFragment();
                }
            }
        return cartFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找到控件
        initView(view);
        int[] imgId = getImgId();
        String[] text = getText();
        datas = new ArrayList<>();
        for (int i = 0; i < imgId.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("logo", imgId[i]);
            map.put("text", text[i]);
            datas.add(map);
        }
        //设置适配器、创建适配器
        gv.setAdapter(new MyAdapter());
        //GrildView的点击事件
        gvChoose();
        //RadioGroup的点击事件
        rgChoose();
        //Button、LinearLayout的点击事件
        llChoose();

    }
    //找到控件
    private void initView(View view) {
        gv = (GridView) view.findViewById(R.id.gv);
        rgPerson = (RadioGroup) view.findViewById(R.id.radio_group);
        ll_bottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        ll_top = (LinearLayout) view.findViewById(R.id.ll_top);
        bt_login = (Button) view.findViewById(R.id.bt_login);
        bt_register = (Button) view.findViewById(R.id.bt_register);
    }

    //LinearLayout的点击事件
    private void llChoose() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"登录",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), FiveLoginActivity.class));
            }
        });
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"注册",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), FiveRegisterActivity.class));
            }
        });
        ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("GET","||-------------->我的订单，查看全部订单");
                startActivity(new Intent(getContext(), FiveTopActivity.class));
            }
        });
        ll_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("GET","||-------------->设置，当前版本");
                startActivity(new Intent(getContext(), FiveBottomActivity.class));
            }
        });
    }

    //RadioGroup的点击事件
    private void rgChoose() {
        rgPerson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_pay://代付款
                        //Log.i("GET","||-------------->代付款");
                        startActivity(new Intent(getContext(), FiveTop1Activity.class));
                        break;
                    case R.id.rb_delivery://待发货
                        //Log.i("GET","||-------------->待发货");
                        startActivity(new Intent(getContext(), FiveTop2Activity.class));
                        break;
                    case R.id.rb_goods://待收货
                        //Log.i("GET","||-------------->待收货");
                        startActivity(new Intent(getContext(), FiveTop3Activity.class));
                        break;
                    case R.id.rb_evaluation://待评价
                        //Log.i("GET","||-------------->待评价");
                        startActivity(new Intent(getContext(), FiveTop4Activity.class));
                        break;
                }
            }
        });
    }

    //GrildView的点击事件
    private void gvChoose() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), "ads"+position, Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        //Toast.makeText(getContext(), "购物车", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody1Activity.class));
                        break;
                    case 1:
                        //Toast.makeText(getContext(), "收藏夹", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody2Activity.class));
                        break;
                    case 2:
                        //Toast.makeText(getContext(), "收货地址", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody3Activity.class));
                        break;
                    case 3:
                        //Toast.makeText(getContext(), "分享应用", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(getContext(), FiveBody4Activity.class));
                        //分享应用
                        sharedpreferences();
                        break;
                    case 4:
                        //Toast.makeText(getContext(), "天猫超市", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody5Activity.class));
                        break;
                    case 5:
                        //Toast.makeText(getContext(), "阿里旅行", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody6Activity.class));
                        break;
                    case 6:
                        //Toast.makeText(getContext(), "订机票", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody7Activity.class));
                        break;
                    case 7:
                        //Toast.makeText(getContext(), "天天特价", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody8Activity.class));
                        break;
                    case 8:
                        //Toast.makeText(getContext(), "浏览记录", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody9Activity.class));
                        break;
                    case 9:
                        //Toast.makeText(getContext(), "帮助中心", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody10Activity.class));
                        break;
                    case 10:
                        //Toast.makeText(getContext(), "意见反馈", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody11Activity.class));
                        break;
                    case 11:
                        //Toast.makeText(getContext(), "忘记密码", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody12Activity.class));
                        break;
                    case 12:
                        //Toast.makeText(getContext(), "注册账号", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveRegisterActivity.class));
                        break;
                    case 13:
                        //Toast.makeText(getContext(), "为我点赞", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), FiveBody14Activity.class));
                        break;
//                    case 14:
//                        //退出账号
//                        DataCleanManager.cleanApplicationData(getContext());
//                        Toast.makeText(getContext(), "退出出成功", Toast.LENGTH_LONG).show();
//                        break;
                }
            }


        });
    }
    //TODO:的三方分享############################################
    //回调监听
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(getContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    //第三方分享
    private void sharedpreferences() {
        //设置分享的平台
        SHARE_MEDIA[] list = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE };
        new ShareAction(getActivity())
                .setDisplayList(list)//设置分享的平台列表
                //分享的内容
                .withText("有一大波便宜包邮的好货正在出没，快加入购物车吧")
                //分享的标题
                .withTitle("购物的小车说满就满")
                //多媒体:图片,音频,视频,表情等
                .withMedia(new UMImage(getContext(), R.drawable.ic_category_baoyou))
                //分享一个链接
                .withTargetUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.oppa.qz1yuan")
                .setCallback(umShareListener)//分享的监听
                .open();//打开分享面板
    }

    /*图片*/
    private int[] getImgId() {
        return new int[]{R.mipmap.five_image_body1, R.mipmap.five_image_body2,
                R.mipmap.five_image_body3, R.mipmap.five_image_body4,
                R.mipmap.five_image_body5, R.mipmap.five_image_body6,
                R.mipmap.five_image_body7, R.mipmap.five_image_body8,
                R.mipmap.five_image_body9, R.mipmap.five_image_body10,
                R.mipmap.five_image_body11, R.mipmap.five_image_body12,
                R.mipmap.five_image_body13, R.mipmap.five_image_body14,
                };/*R.mipmap.five_image_body15*/
    }
    /*文字*/
    private String[] getText() {
        return new String[]{"购物车", "收藏夹", "收货地址", "分享应用",
                "天猫超市", "阿里旅行", "订机票", "天天特价",
                "浏览记录", "帮助中心", "意见反馈", "忘记密码",
                "注册账号", "为我点赞"};/*,"退出账号"*/
    }

    @Override
    public void onClick(View v) {

    }

    //GrildView的适配器
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //适配器
            view = LayoutInflater.from(getContext()).inflate(R.layout.grild_list, viewGroup, false);
            Map<String, Object> map = datas.get(i);
            int imgId = (int) map.get("logo");
            String text = (String) map.get("text");
            ImageView ivLogo = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            ivLogo.setImageResource(imgId);
            tvItem.setText(text);
            return view;
        }
    }
}
