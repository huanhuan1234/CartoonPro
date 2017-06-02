package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.bean.DiscoverBean;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/29
 */

public class MyAdviceBaseAdapter extends BaseAdapter{
    private Context mContext;
    private List<DiscoverBean.DataBean.InfosBean> mInfosBean;

    public MyAdviceBaseAdapter(Context context, List<DiscoverBean.DataBean.InfosBean> infosBean) {
        mContext = context;
        mInfosBean = infosBean;
    }

    final int TYPE_1=0;
    final int TYPE_2=1;
    final int TYPE_3=2;
    final int TYPE_4=3;
    private int num;
    @Override
    public int getCount() {
        return 8;
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
        ViewHolder hodler;
        int type= getItemViewType(position);
        if(convertView==null){
            hodler=new ViewHolder();
            switch (type){
                case TYPE_1:
                    convertView=View.inflate(mContext, R.layout.discovertypeone,null);
                    hodler.mTextView1= (TextView) convertView.findViewById(R.id.discover_xlv_title);
                    hodler.mGridView= (GridView) convertView.findViewById(R.id.discover_xlv_gv);
                    break;
                case TYPE_2:
                    convertView=View.inflate(mContext, R.layout.discovertypetwo,null);
                    hodler.mTextView1= (TextView) convertView.findViewById(R.id.discover_xlv_title);
                    hodler.mGridView= (GridView) convertView.findViewById(R.id.discover_xlv_gv2);
                    break;
                case TYPE_3:
                    convertView=View.inflate(mContext, R.layout.discovertypethree,null);
                    hodler.mTextView1= (TextView) convertView.findViewById(R.id.discover_xlv_title);
                    hodler.mListView= (ListView) convertView.findViewById(R.id.discover_xlv_lv);
                    break;
                case TYPE_4:
                    convertView=View.inflate(mContext, R.layout.discovertypefour,null);
                    hodler.mTextView1= (TextView) convertView.findViewById(R.id.discover_xlv_title);
                    hodler.mGridView= (GridView) convertView.findViewById(R.id.discover_xlv_gv3);
                    break;
            }
            convertView.setTag(hodler);
        }else{
            hodler= (ViewHolder) convertView.getTag();
        }

        switch (type){
            case TYPE_1:
                hodler.mTextView1.setText(mInfosBean.get(5).getTitle()+"");
                List<DiscoverBean.DataBean.InfosBean.TopicsBean> topics = mInfosBean.get(5).getTopics();
                AdviceGridViewAdapter adviceGridViewAdapter = new AdviceGridViewAdapter(mContext,topics);
                hodler.mGridView.setAdapter(adviceGridViewAdapter);
                break;
            case TYPE_2:
                hodler.mTextView1.setText(mInfosBean.get(6).getTitle()+"");
                List<DiscoverBean.DataBean.InfosBean.TopicsBean> topics2 = mInfosBean.get(6).getTopics();
                AdviceGridViewAdapter2 adviceGridViewAdapter2 = new AdviceGridViewAdapter2(mContext,topics2);
                hodler.mGridView.setAdapter(adviceGridViewAdapter2);
                break;
            case TYPE_3:
                hodler.mTextView1.setText(mInfosBean.get(7).getTitle()+"");
                List<DiscoverBean.DataBean.InfosBean.TopicsBean> topics4 = mInfosBean.get(7).getTopics();
                AdviceGridViewAdapter2 adviceGridViewAdapter4 = new AdviceGridViewAdapter2(mContext,topics4);
                hodler.mListView.setAdapter(adviceGridViewAdapter4);
                break;

            case TYPE_4:
                hodler.mTextView1.setText(mInfosBean.get(8).getTitle()+"");
                List<DiscoverBean.DataBean.InfosBean.TopicsBean> topics3 = mInfosBean.get(8).getTopics();
                AdviceGridViewAdapter3 adviceGridViewAdapter3 = new AdviceGridViewAdapter3(mContext,topics3);
                hodler.mGridView.setAdapter(adviceGridViewAdapter3);
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        num=position%8;
        if(num==0 || num==2 || num==4){
            return TYPE_1;
        }else if(num==1){
            return TYPE_2;
        }else if(num==3 || num>=7) {
            return TYPE_3;
        }else {
            return TYPE_4;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    private class ViewHolder{
        TextView mTextView1;
        GridView  mGridView;
        ListView  mListView;
    }

}
