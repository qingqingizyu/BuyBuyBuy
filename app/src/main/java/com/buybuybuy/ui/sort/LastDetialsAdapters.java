package com.buybuybuy.ui.sort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.buybuybuy.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class LastDetialsAdapters extends BaseAdapter {
private List<DetailsGoods> mData;
    private Context mContext;
    private ViewHolder holder;

    public LastDetialsAdapters(Context context){
        this.mContext = context;

    }
    public void setDate(List<DetailsGoods> date){
        this.mData = date;
    }
    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mData!=null?mData.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==null){
           convertView =  LayoutInflater.from(mContext).inflate(R.layout.details_item_layout,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DetailsGoods detailsGoods = mData.get(position);
        holder.tvTitle.setText(detailsGoods.getTitle());
        holder.tvPrice.setText("$"+detailsGoods.getPrice());
        holder.tvSource.setText("包邮");
        holder.tvSale.setText("已售"+detailsGoods.getSell_count()+"件");
        if (detailsGoods.getSource().equals("tmall")){
            holder.tvPlace.setText("去天猫");
        }else {
            holder.tvPlace.setText("去淘宝");
        }
        String logo = detailsGoods.getImg();
        Glide.with(mContext).load(logo).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.bg_list_item).into(holder.ivLogo);
        return convertView;
    }
    class ViewHolder{
         ImageView ivLogo;
        TextView tvTitle,tvPrice,tvSource,tvSale,tvPlace;
        public ViewHolder (View convertView){
            ivLogo = (ImageView) convertView.findViewById(R.id.iv_detail);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_details_title);
            tvPrice = (TextView) convertView.findViewById(R.id.tv_price1);
            tvSource = (TextView) convertView.findViewById(R.id.tv_source1);
            tvSale = (TextView) convertView.findViewById(R.id.tv_sale);
            tvPlace = (TextView) convertView.findViewById(R.id.tv_place);

        }
    }
}
