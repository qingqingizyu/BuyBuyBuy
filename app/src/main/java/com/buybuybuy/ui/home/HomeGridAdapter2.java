package com.buybuybuy.ui.home;

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
import com.buybuybuy.bean.Home2Bean;

/**
 * Created by Mr.J on 2016/10/12.
 */

public class HomeGridAdapter2 extends BaseAdapter {
    private Home2Bean mData;
    private Context mContext;
    private LayoutInflater mInflater;


    public HomeGridAdapter2(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setHomeData2(Home2Bean list){
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
            convertView = mInflater.inflate(R.layout.list_item_hot_1, parent, false);
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
            holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        }
        if (mData.getData().get(position).getSource()!=null){
            if ("tmall".equals(mData.getData().get(position).getSource())){
                holder.tvSource.setText("天猫");
            }else {
                holder.tvSource.setText("淘宝");
            }
            holder.tvSource.setText(mData.getData().get(position).getSource());
        }

        if (mData.getData().get(position).getImg()!= null){
            String ivImg = mData.getData().get(position).getImg();
            Glide.with(mContext).load(ivImg).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.bg_list_item).into(holder.ivImg);
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
