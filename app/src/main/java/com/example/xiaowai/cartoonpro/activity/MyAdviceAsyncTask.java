package com.example.xiaowai.cartoonpro.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.example.xiaowai.cartoonpro.Utils.GsonUtils;
import com.example.xiaowai.cartoonpro.adapter.MyAdviceBaseAdapter;
import com.example.xiaowai.cartoonpro.adapter.MyAdvicePagerAdapter;
import com.example.xiaowai.cartoonpro.bean.DiscoverBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class MyAdviceAsyncTask extends AsyncTask<String,Integer,DiscoverBean>{

    private StringBuffer sb;
    private ViewPager vp;
    private int count;
    private XListView mXListView;
    private Context mContext;

    public MyAdviceAsyncTask(ViewPager vp, XListView XListView, Context context) {
        this.vp = vp;
        mXListView = XListView;
        mContext = context;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10:
                    count++;
                    vp.setCurrentItem(count);
                    handler.sendEmptyMessageDelayed(10,3000);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onPostExecute(DiscoverBean db) {
        super.onPostExecute(db);
        if(db!=null){
            DiscoverBean.DataBean data = db.getData();
            if(data!=null){
                List<DiscoverBean.DataBean.InfosBean> infos = data.getInfos();
                if(infos.size()!=0){
                    DiscoverBean.DataBean.InfosBean infosBean = infos.get(0);
                    if(infosBean!=null){
                        List<DiscoverBean.DataBean.InfosBean.BannersBean> banners = infosBean.getBanners();
                        if(banners.size()!=0){
                            MyAdvicePagerAdapter myAdvicePagerAdapter = new MyAdvicePagerAdapter(mContext,banners);
                            vp.setAdapter(myAdvicePagerAdapter);
                            handler.sendEmptyMessageDelayed(10,3000);
                        }
                    }
                    MyAdviceBaseAdapter myAdviceBaseAdapter = new MyAdviceBaseAdapter(mContext,infos);
                    mXListView.setAdapter(myAdviceBaseAdapter);
                }

            }
        }

    }

    @Override
    protected DiscoverBean doInBackground(String...param) {
        String s = doGet(param[0]);
        if(s!=null){
            DiscoverBean discoverBean = GsonUtils.analysisGson(s, DiscoverBean.class);
            if(discoverBean!=null){
                return discoverBean;
            }
        }
        return null;
    }

    public String doGet(String uri){
        sb = new StringBuffer();
        try {
            URL url=new URL(uri);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2000);
            if(conn.getResponseCode()==200){
                InputStream inputStream = conn.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                String line="";
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                return sb.toString();
            }
        }catch (Exception e){

        }
        return null;
    }
}
