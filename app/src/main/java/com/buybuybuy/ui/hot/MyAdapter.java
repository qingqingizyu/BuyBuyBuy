package com.buybuybuy.ui.hot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buybuybuy.R;
import com.buybuybuy.bean.SearchBean;

import java.util.List;

/**
 * 类描述:
 * 创建人:一一哥
 * 创建时间:16/9/13 15:11
 * 备注:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<SearchBean> mList;

    private MyItemClickListener mItemClickListener;

    public interface MyItemClickListener {
         void onItemClick(View view,int postion);
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }


    public MyAdapter(List<SearchBean> list) {
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchitem, parent, false);
        return new MyViewHolder(view,mItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SearchBean bean = mList.get(position);
        holder.tvName.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvName;

        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            mItemClickListener = listener;
            tvName = (TextView) itemView.findViewById(R.id.tv_search);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(itemView,getPosition());
        }
    }
}
