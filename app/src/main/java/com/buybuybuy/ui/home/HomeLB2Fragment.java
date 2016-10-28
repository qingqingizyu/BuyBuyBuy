package com.buybuybuy.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.buybuybuy.R;
import com.buybuybuy.bean.HomeBean;
import com.buybuybuy.http.OkHttpClientUtils;
import com.buybuybuy.ui.sort.DetaillastActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mr.J on 2016/10/13.
 */

public class HomeLB2Fragment extends Fragment {
    private ListView mListView;

    /**
     * 存放图片的viewpager
     */
    private ViewPager myViewPager;
    /**
     * viewpager的adapter
     */
    private HomeLBAdapter mAdapter;
    /**
     * 存放imageview的集合
     */
    private List<ImageView> imageViews;
    private ImageView imageView;
    /**
     * 在java中i++和++i都是线程不安安全的所以用AtomicInteger
     */
    private AtomicInteger mAtomicInteger = new AtomicInteger(0);
    /**
     * 在图片上显示的小圆点
     */
    private LinearLayout viewGroup;
    /**
     * 小圆点集合
     */
    private ImageView[] groupViews;
    private ImageView groupView;

    private String[] urls = {
            "http://app1101060396.qzoneapp.com/uploads/A0/14/A0141CC2D306A1FE492C7F71135B3463.jpg",
            "http://app1101060396.qzoneapp.com/uploads/5C/17/5C17D1D8012279E31DE1F1E65B1496DB.jpg",
            "http://img.alicdn.com/imgextra/i2/168487437/TB2l8iwXRLzQeBjSZFCXXXmtXXa_!!168487437.jpg",
            "http://app1101060396.qzoneapp.com/assets/v1/img/slides/2016070401.jpg",
            "http://app1101060396.qzoneapp.com/assets/v1/img/slides/pinpai.png",
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                HomeGridAdapter home = new HomeGridAdapter(getActivity());
                home.setHomeData((HomeBean) msg.obj);
                mListView.setAdapter(home);
                mListView.setLayoutParams(getListViewParams());
                home.notifyDataSetChanged();
            }else if (msg.what == 2){
                myViewPager.setCurrentItem((Integer) msg.obj);
            }
        }
    };

    public HomeLB2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_lb, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.home_listView);
        myViewPager = (ViewPager) view.findViewById(R.id.home_vp);
        myViewPager.setOnPageChangeListener(new PagerListener());
        viewGroup = (LinearLayout) view.findViewById(R.id.home_ll_points);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeBean.DataBean.GoodsBean homeBean = (HomeBean.DataBean.GoodsBean) parent.getItemAtPosition(position);
                String url = homeBean.getUrl();
                Intent intent = new Intent(getActivity(), DetaillastActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        initLoaderPic();
        /*
         * ViewPager如果放到ScrollView当中，需要在Java代码中通过LayoutParams动态的
         * 设置一个固定值的高，否则ViewPager中的内容无法显示
         */
        // 1、获取屏幕密度
        float desity = getResources().getDisplayMetrics().density;
        // 2、获取ViewPager在不同屏幕密度上的手机的高度
        int viewPagerHeight = (int) (200 * desity);
        // 3、通过setLayoutParams方式，给ViewPager动态设置高度
        myViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, viewPagerHeight));
    }

    /**
     * 初始化imageloader
     */
    private void initLoaderPic() {
        DisplayImageOptions mImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.bg_list_item) // 设置加载过程中显示的图片
                .showImageForEmptyUri(R.drawable.bg_list_item)// 设置图片的链接地址为空的时候的加载的图片
                .showImageOnFail(R.drawable.bg_list_item) // 设置图片加载失败的时候的图片
                .cacheInMemory(true) // 设置是否加入缓存
                .cacheOnDisc(true) // 设置是否缓存到sd卡上去
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型，默认是ARGB_8888
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示（图像将被二次采用的整倍数）
                .build();

        ImageLoaderConfiguration mConfiguration = new ImageLoaderConfiguration.Builder(getActivity())
                .threadPriority(Thread.NORM_PRIORITY) // 设置线程池的数量
                .denyCacheImageMultipleSizesInMemory() // 不同大小图片只有一个缓存，默认是多个
                .memoryCacheSize(4 * 1024 * 1024) // 设置缓存的大小
                .tasksProcessingOrder(QueueProcessingType.LIFO)// 设置图片下载和显示的工作对列顺序
                .defaultDisplayImageOptions(mImageOptions)
                .build();

        ImageLoader.getInstance().init(mConfiguration);
        addView();
    }

    /**
     * 获取图片并加入到集合中去
     */
    private void addView() {
        imageViews = new ArrayList<>();
        //如果你想要一个效果但是又不想在代码中实现，那么就可以去定义一个布局去加载。
        for (int i = 0; i < urls.length; i++) {
            imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(urls[i], imageView);
            imageViews.add(imageView);
        }
        //获取图片以后的才去设定小圆点的个数
        initCirclePoint();
        mAdapter = new HomeLBAdapter(imageViews);
        myViewPager.setAdapter(mAdapter);
        new Thread(){

            private String data;
            private HomeBean home4Bean;
            @Override
            public void run() {
                try {
                    //请求数据
                    data = OkHttpClientUtils.getStringFromUrl(Constants.NvSJX);
                    home4Bean = new Gson().fromJson(data, HomeBean.class);
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = home4Bean;
                    mHandler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //这里呢是启动异步工作线程去不断的对图片进行转换
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    atomicOption();
                    Message message = Message.obtain();
                    message.what = 2;
                    int i = mAtomicInteger.get();
                    message.obj = i;
                    mHandler.sendMessage(message);
                }
            }
        }).start();

    }

    /**
     * 设置要显示的小圆点
     */
    private void initCirclePoint() {
        groupViews = new ImageView[imageViews.size()];
        for (int i = 0; i < groupViews.length; i++) {
            groupView = new ImageView(getActivity());
            groupView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            groupViews[i] = groupView;
            //初始化默认选择第一张
            if (i == 0) {
                groupViews[i].setBackgroundResource(R.drawable.point_gray_bg);
            } else {
                groupViews[i].setBackgroundResource(R.drawable.point_red_bg);
            }
            //将小圆点放入到布局中去
            viewGroup.addView(groupViews[i]);
        }

    }

    private void atomicOption() {
        mAtomicInteger.incrementAndGet();
        if (mAtomicInteger.get() > groupViews.length - 1) {
            mAtomicInteger.addAndGet(-groupViews.length);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    class PagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {//页面状态改变的时候触发

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {//页面滚动的时候触发
        }

        @Override
        public void onPageSelected(int arg0) {
            //获取当前页面
            mAtomicInteger.getAndSet(arg0);
            for (int i = 0; i < groupViews.length; i++) {
                if (arg0 == i) {
                    groupViews[i].setBackgroundResource(R.drawable.point_gray_bg);
                } else {
                    groupViews[i].setBackgroundResource(R.drawable.point_red_bg);
                }
            }
        }
    }
    /**
     * 动态的算出ListView实际的LayoutParams
     * 最关键的是算出LayoutParams.height
     */
    public ViewGroup.LayoutParams getListViewParams() {
        //通过ListView获取其中的适配器adapter
        ListAdapter listAdapter = mListView.getAdapter();

        //声明默认高度为0
        int totalHeight = 0;
        //便利ListView所有的item，累加所有item的高度就是ListView的实际高度
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, mListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //将累加获取的totalHeight赋值给LayoutParams的height属性
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = totalHeight + (mListView.getDividerHeight() * (listAdapter.getCount() - 1));
        return params;
    }


}
