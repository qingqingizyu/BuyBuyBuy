package com.buybuybuy.ui.hot;

/*
 * @author: ZTC
 * @Description: baseAdapter适配器
 * @创建时间:  2016/9/10 18:30 
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.buybuybuy.R;
import com.buybuybuy.bean.HotBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HotAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<HotBean> mData;
    private Context mContext;

    public HotAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    //传递数据方法
    public void setData(List<HotBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //绑定控件
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_hot_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        HotBean hotBean = mData.get(position);
        holder.tvTitle.setText(hotBean.getTitle());
        holder.tvOriginalPrice.setText("￥"+hotBean.getOriginal_price());
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        holder.tvBuyNum.setText(hotBean.getBuy_num());
        holder.tvPrice.setText("￥"+hotBean.getPrice());
        if ("tmall".equals(hotBean.getSource())){
            holder.tvSource.setText("天猫");
        }else {
            holder.tvSource.setText("淘宝");
        }
        Glide.with(mContext).load(hotBean.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.bg_list_item).into(holder.ivImg);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_original_price)
        TextView tvOriginalPrice;
        @BindView(R.id.tv_buy_num)
        TextView tvBuyNum;
        @BindView(R.id.tv_source)
        TextView tvSource;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
