package com.example.xiaowai.cartoonpro.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.xiaowai.cartoonpro.Utils.GsonUtils;
import com.example.xiaowai.cartoonpro.adapter.HotFiveAdapter;
import com.example.xiaowai.cartoonpro.bean.FiveBean;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static android.R.id.list;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class MyAsyncTask extends AsyncTask{
    private String s;
    private Context context;
    private Handler handler;

    public MyAsyncTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(s!=null){
            FiveBean fiveBean = GsonUtils.analysisGson(s, FiveBean.class);
            if(fiveBean!=null){
                FiveBean.DataBean data = fiveBean.getData();
                if(data!=null){
                    List<FiveBean.DataBean.ComicsBean> comics = data.getComics();
                    if(comics!=null){
                        Message message = Message.obtain();
                        message.what=1;
                        message.obj=comics;
                        handler.sendMessage(message);
                        Log.d("zzz",comics.size()+"");
                    }
                }
            }
        }
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        HttpClient client=new DefaultHttpClient();
        HttpGet httpGet = new HttpGet((String) params[0]);
        try {
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                ByteArrayOutputStream aos=new ByteArrayOutputStream();
                int len=0;
                byte [] b=new byte[1024];
                while((len=inputStream.read(b))!=-1){
                    aos.write(b,0,len);
                    //Thread.sleep(200);
                }
                s = aos.toString();
                Log.d("zzz",s+"");
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
