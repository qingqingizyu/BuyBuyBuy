package com.buybuybuy.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.buybuybuy.R;
import com.buybuybuy.bean.Home2Bean;

/**
 * Created by Mr.J on 2016/10/12.
 */

public class HomeGridAdapter22 extends BaseAdapter {
    private Home2Bean mData;
    private Context mContext;
    private LayoutInflater mInflater;


    public HomeGridAdapter22(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setHomeData22(Home2Bean list){
        this.mData = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData != null ? mData.getData().size():0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ?mData.getData().get(position): 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_hot_2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
//        if(home.getSlides() != null){
//            //轮播
//        }
        if (mData.getData().get(position).getTitle()!=null){
            holder.tvTitle.setText(mData.getData().get(position).getTitle());
        }
        if (mData.getData().get(position).getBuy_num()!=null){
            holder.tvBuyNum.setText(mData.getData().get(position).getBuy_num());
        }
        if (mData.getData().get(position).getPrice()!=null){
            holder.tvPrice.setText(mData.getData().get(position).getPrice());
        }
        if (mData.getData().get(position).getOriginal_price()!=null){
            holder.tvOriginalPrice.setText(mData.getData().get(position).getOriginal_price());
        }
        if (mData.getData().get(position).getSource()!=null){
            holder.tvSource.setText(mData.getData().get(position).getSource());
        }

        if (mData.getData().get(position).getImg()!= null){
            String ivImg = mData.getData().get(position).getImg();
            Glide.with(mContext).load(ivImg).placeholder(R.mipmap.ic_launcher).into(holder.ivImg);
        }

        return convertView;
    }

    class ViewHolder{

        private final ImageView ivImg;
        private final TextView tvTitle;
        private final TextView tvPrice;
        private final TextView tvOriginalPrice;
        private final TextView tvBuyNum;
        private final TextView tvSource;

        public ViewHolder(View converView){
            ivImg = (ImageView) converView.findViewById(R.id.iv_img);
            tvTitle = (TextView) converView.findViewById(R.id.tv_title);
            tvPrice = (TextView) converView.findViewById(R.id.tv_price);
            tvOriginalPrice = (TextView) converView.findViewById(R.id.tv_original_price);
            tvBuyNum = (TextView) converView.findViewById(R.id.tv_buy_num);
            tvSource = (TextView) converView.findViewById(R.id.tv_source);
        }
    }
}
