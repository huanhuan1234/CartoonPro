package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xiaowai.cartoonpro.bean.DiscoverBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/29
 */

public class MyAdvicePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<DiscoverBean.DataBean.InfosBean.BannersBean> mBannersBeen;

    public MyAdvicePagerAdapter(Context context, List<DiscoverBean.DataBean.InfosBean.BannersBean> bannersBeen) {
        mContext = context;
        mBannersBeen = bannersBeen;
    }

    @Override
    public int getCount() {
        return mBannersBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoader.getInstance().displayImage(mBannersBeen.get(position%mBannersBeen.size()).getPic(),imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

}
