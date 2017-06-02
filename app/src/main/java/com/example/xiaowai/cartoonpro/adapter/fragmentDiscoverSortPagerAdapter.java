package com.example.xiaowai.cartoonpro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/16
 */

public class fragmentDiscoverSortPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private Context context;

    public fragmentDiscoverSortPagerAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
