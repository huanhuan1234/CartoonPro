package com.example.xiaowai.cartoonpro.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/24
 */

public class HotTzPagerAdapter  extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private List<String> tablist;

    public HotTzPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tablist) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tablist = tablist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position%tablist.size());

    }

}
