package com.buybuybuy.ui.sort;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.buybuybuy.R;

import java.util.ArrayList;
import java.util.List;

public class DetialsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RadioGroup radioGroup;
    private FrameLayout flDetail;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detials);
        //初始化控件
        initView();
        Intent intent = getIntent();
        String titles = intent.getStringExtra("names");
        String cid = intent.getStringExtra("cid");
        tvTitle.setText(titles);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String path = Contasts.RENQI;
        StringBuilder builder = new StringBuilder();
       // 17&page=1&size=50&type=2
        StringBuilder append = builder.append(path).append(cid).append("@page=1&size=50");

        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            Fragment fragment = null;
            switch (i){
                case 0:
                    fragment = new DetailGridFragment();
                    Bundle bundle = new Bundle();
                    String moren = append.append("&type=1").toString();
                    bundle.putString("path",moren);
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    fragment = new DetailGridFragment();
                    Bundle bundle1 = new Bundle();
                    String renqi = append.append("&type=2").toString();
                    bundle1.putString("path",renqi);
                    fragment.setArguments(bundle1);
                    break;
                case 2:
                    fragment = new DetailGridFragment();
                    Bundle bundle2 = new Bundle();
                    String upPrice = append.append("&sort=0&type=4").toString();
                    String downPrice = append.append("&sort=1&type=4").toString();
                    bundle2.putString("path",upPrice);
                    bundle2.putString("path1",downPrice);
                    fragment.setArguments(bundle2);
                    break;
                case 3:
                    fragment = new DetailGridFragment();
                    Bundle bundle3 = new Bundle();
                    String xiaoliang = append.append("&type=3").toString();
                    bundle3.putString("path",xiaoliang);
                    fragment.setArguments(bundle3);
                    break;
            }
            fragments.add(fragment);
        }
        //默认加载第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fr_detail_three, fragments.get(1)).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                         getSupportFragmentManager().beginTransaction().replace(R.id.fr_detail_three, fragments.get(i)).commit();
                    }
                }
            }
        });



    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.img_detail_three);
        tvTitle = (TextView) findViewById(R.id.tv_title_three);
        radioGroup = (RadioGroup) findViewById(R.id.rd_detail);
        flDetail = (FrameLayout) findViewById(R.id.fr_detail_three);
    }
}
