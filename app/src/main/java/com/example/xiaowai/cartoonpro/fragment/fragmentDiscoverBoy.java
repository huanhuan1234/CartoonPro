package com.example.xiaowai.cartoonpro.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.activity.MyAdviceAsyncTask;
import com.example.xiaowai.cartoonpro.adapter.fragmentDiscoverPagerAdapter;
import com.example.xiaowai.cartoonpro.adapter.fragmentDiscoverSortPagerAdapter;
import com.example.xiaowai.cartoonpro.bean.Url;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/23
 */

public class fragmentDiscoverBoy extends Fragment{

    private View view;
    private List<View> list=new ArrayList<View>();
    private ViewPager fragment_discover_vp;
    private RadioGroup radoiogroup_discover;
    private View sortView;
    private ViewPager sort_vp;
    private List<Fragment> sortlist=new ArrayList<Fragment>();
    private MagicIndicator indicator;
    private List<String> dataList=new ArrayList<>();
    private View mAdviceView;
    private ViewPager mAdvice_vp;
    private XListView mDiscover_xlv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover_boy,null);
        findView();
        return view;
    }

    private void initDataList(){
        dataList.add("全部");
        dataList.add("少年");
        dataList.add("奇幻");
        dataList.add("爆笑");
        dataList.add("灵异");
        dataList.add("剧情");
        dataList.add("都市");
        dataList.add("古风");
        dataList.add("日常");
        dataList.add("治愈");
        dataList.add("恋爱");
        dataList.add("完结");
    }

    private void initView(){
        if(dataList.size()==0){
            initDataList();
        }
        mAdviceView = View.inflate(getActivity(), R.layout.fragment_discover_layout_advice,null);
        list.add(mAdviceView);
        findAdviceView();
        sortView = View.inflate(getActivity(), R.layout.fragment_discover_layout_sort,null);
        list.add(sortView);
        findSortView();
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(dataList.get(index));
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.YELLOW);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sort_vp.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        indicator.setNavigator(commonNavigator);
        if(sortlist.size()==0){
            initSortData();
        }
        fragmentDiscoverSortPagerAdapter sortPagerAdapter = new fragmentDiscoverSortPagerAdapter(getActivity().getSupportFragmentManager(), sortlist, getActivity());
        sort_vp.setAdapter(sortPagerAdapter);
        sort_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicator.onPageScrollStateChanged(state);
            }
        });

    }

    private void findAdviceView() {
        mAdvice_vp = (ViewPager) mAdviceView.findViewById(R.id.discover_advice_vp);
        mDiscover_xlv = (XListView) mAdviceView.findViewById(R.id.discover_xlv);
        new MyAdviceAsyncTask(mAdvice_vp,mDiscover_xlv,getActivity()).execute(Url.urldiscover);
    }

    private void initSortData(){
        for (int i = 0; i < 12; i++) {
            fragmentDiscoverSortAll discoverSortAll = new fragmentDiscoverSortAll();
            Bundle bundle = new Bundle();
            bundle.putSerializable("tabIndex", i);
            discoverSortAll.setArguments(bundle);
            sortlist.add(discoverSortAll);
        }
    }

    private void findSortView(){
        sort_vp = (ViewPager) sortView.findViewById(R.id.fragment_discover_sort_vp);
        indicator = (MagicIndicator) sortView.findViewById(R.id.sortmagicIndicator);
    }

    private void findView(){
        radoiogroup_discover = (RadioGroup) view.findViewById(R.id.radoiogroup_discover);
        fragment_discover_vp = (ViewPager) view.findViewById(R.id.fragment_discover_vp);
        initView();
        fragmentDiscoverPagerAdapter adapter = new fragmentDiscoverPagerAdapter(list, getActivity());
        fragment_discover_vp.setAdapter(adapter);
        radoiogroup_discover.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbAdvice:
                        fragment_discover_vp.setCurrentItem(0);
                        break;
                    case R.id.rbSort:
                        fragment_discover_vp.setCurrentItem(1);
                        break;
                }
            }
        });
        fragment_discover_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radoiogroup_discover.check(R.id.rbAdvice);
                        break;
                    case 1:
                        radoiogroup_discover.check(R.id.rbSort);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
