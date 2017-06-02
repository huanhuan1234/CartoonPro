package com.example.xiaowai.cartoonpro.Application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/22
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader(){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480,960).build();
        ImageLoader.getInstance().init(configuration);
    }
}