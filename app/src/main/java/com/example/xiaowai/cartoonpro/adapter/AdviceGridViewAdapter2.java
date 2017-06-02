package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.DiscoverBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/29
 */

public class AdviceGridViewAdapter2 extends BaseAdapter{
    private Context mContext;
    private List<DiscoverBean.DataBean.InfosBean.TopicsBean> mList;

    public AdviceGridViewAdapter2(Context context, List<DiscoverBean.DataBean.InfosBean.TopicsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.advice_xlv_gv_item,null);
            holder.mTextView1= (TextView) convertView.findViewById(R.id.advice_gv_item_tv1);
            holder.mTextView2= (TextView) convertView.findViewById(R.id.advice_gv_item_tv2);
            holder.img= (ImageView) convertView.findViewById(R.id.advice_gv_item_image);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mTextView1.setText(mList.get(position).getTitle()+"");
        holder.mTextView2.setText(mList.get(position).getLabel_text()+"");
        ImageLoader.getInstance().displayImage(mList.get(position).getPic(),holder.img);
        return convertView;
    }

    private class ViewHolder{
        TextView mTextView1;
        TextView mTextView2;
        ImageView img;
    }
}
