package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentSocialSquarePagerAdapter extends PagerAdapter{
    private List<View> list;
    private Context context;

    public fragmentSocialSquarePagerAdapter(List<View> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
