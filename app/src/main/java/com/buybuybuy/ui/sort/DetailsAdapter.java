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


public class DetailsAdapter extends BaseAdapter {
    private List<KindMain> mData;
    private ViewHolder holder;
    private String mName;
    private Context mContext;

    public DetailsAdapter(List<KindMain> data,String name,Context context){
       this.mData = data;
        this.mName = name;
        this.mContext = context;
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
        if (convertView==null){
          convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        KindMain kindMain = mData.get(position);
                holder.tvDes.setText(kindMain.getName());
                String logo = kindMain.getImg();
                Glide.with(mContext).load(logo).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.bg_list_item).into(holder.ivLogo);
        return convertView;
    }
    class ViewHolder{
        ImageView ivLogo;
        TextView tvDes;
        public ViewHolder(View convertView){
            ivLogo = (ImageView) convertView.findViewById(R.id.iv_main);
            tvDes = (TextView) convertView.findViewById(R.id.tv_main);
        }
    }
}
