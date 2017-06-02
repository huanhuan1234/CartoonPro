package com.example.xiaowai.cartoonpro.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.activity.MyAsyncTask;
import com.example.xiaowai.cartoonpro.adapter.HotFiveAdapter;
import com.example.xiaowai.cartoonpro.bean.FiveBean;
import com.example.xiaowai.cartoonpro.bean.Url;

import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentComicHotFive extends Fragment{

    private View view;
    private XListView xlv;
    private String [] urlArr={Url.urlfour,Url.urlfive,Url.urlsix,Url.urlseven,Url.urlone,Url.urlyesterday,Url.urltoday};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comic_hot_five,null);
        findView();
        return view;
    }

    private void findView(){
        xlv = (XListView) view.findViewById(R.id.hot_five_lv);
        Handler handler=new Handler(){
            private HotFiveAdapter hfa;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    List<FiveBean.DataBean.ComicsBean> comics= (List<FiveBean.DataBean.ComicsBean>) msg.obj;
                    Log.d("zzz",comics.size()+"");
                    hfa = new HotFiveAdapter(comics,getActivity());
                    xlv.setAdapter(hfa);
                    xlv.setXListViewListener(new XListView.IXListViewListener() {
                        @Override
                        public void onRefresh() {
                             hfa.notifyDataSetChanged();
                             xlv.stopRefresh();
                        }

                        @Override
                        public void onLoadMore() {
                            hfa.notifyDataSetChanged();
                            xlv.stopLoadMore();
                        }
                    });
                }
            }
        };
        Bundle bundle = getArguments();
        int tabIndex = bundle.getInt("tabIndex");
        switch (tabIndex) {
            case 0:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[0]);
                break;
            case 1:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[1]);
                break;
            case 2:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[2]);
                break;
            case 3:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[3]);
                break;
            case 4:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[4]);
                break;
            case 5:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[5]);
                break;
            case 6:
                new MyAsyncTask(getActivity(),handler).execute(urlArr[6]);
                break;
            default:
                break;
        }

    }
}
