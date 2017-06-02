package com.example.xiaowai.cartoonpro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.Utils.WeekUtils;
import com.example.xiaowai.cartoonpro.activity.HotFollowActivity;
import com.example.xiaowai.cartoonpro.adapter.fragmentComicHotPagerAdapter;
import com.example.xiaowai.cartoonpro.adapter.fragmentComicPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentComic extends Fragment {

    private View view;
    private ViewPager vp;
    private RadioButton rbfollow;
    private RadioButton rbhot;
    private RadioGroup radoiogroup_comic;
    private List<View> list;
    private View hotView;
    private List<Fragment> hotlist;
    private ViewPager fragment_comic_hot_vp;
    private List<String> dataList=new ArrayList<>();
    private MagicIndicator indicator;
    private FragmentManager fragmentManager;
    private static String mWay;
    private Calendar calendar;
    private View followView;
    private ImageView mImgfollow;
    private ImageView mImgfollowtext;
    private ImageView mImgfollowtextsocial;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comic,null);
        findView();
        return view;
    }

    private void initViewPagerView(){
        list = new ArrayList<View>();
        followView = View.inflate(getActivity(), R.layout.fragment_comic_layout_follow,null);
        list.add(followView);
        findFollowView();
        hotView = View.inflate(getActivity(), R.layout.fragment_comic_layout_hot,null);
        list.add(hotView);
        findComicView();
    }

    private void findFollowView() {
        mImgfollow = (ImageView) followView.findViewById(R.id.image_follow);
        mImgfollowtext = (ImageView) followView.findViewById(R.id.image_follow_text);
        mImgfollowtextsocial = (ImageView) followView.findViewById(R.id.image_follow_text_discover);
        mImgfollowtextsocial.setVisibility(View.GONE);
        mImgfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HotFollowActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        if(flag){
            mImgfollow.setVisibility(View.GONE);
            mImgfollowtext.setVisibility(View.GONE);
        }
    }

    private void initHotData(){
            for (int i = 0; i < 7; i++) {
                fragmentComicHotFive fchfive = new fragmentComicHotFive();
                Bundle bundle = new Bundle();
                bundle.putSerializable("tabIndex", i);
                fchfive.setArguments(bundle);
                hotlist.add(fchfive);
            }
    }

    private void initDataList(){
        dataList=WeekUtils.getWeek();
    }

    private void findComicView(){
        if(dataList.size()==0){
            initDataList();
        }
        fragmentManager = getActivity().getSupportFragmentManager();
        fragment_comic_hot_vp = (ViewPager) hotView.findViewById(R.id.fragment_comic_hot_vp);
        indicator = (MagicIndicator) hotView.findViewById(R.id.magicIndicator);
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
                        fragment_comic_hot_vp.setCurrentItem(index);
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
        hotlist = new ArrayList<Fragment>();
        if(hotlist.size()==0){
            initHotData();
        }
        fragmentComicHotPagerAdapter adapter = new fragmentComicHotPagerAdapter(getActivity().getSupportFragmentManager(),hotlist,getActivity());
        fragment_comic_hot_vp.setAdapter(adapter);
        fragment_comic_hot_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

    private void findView(){
        vp = (ViewPager) view.findViewById(R.id.fragment_comic_vp);
        radoiogroup_comic = (RadioGroup)view.findViewById(R.id.radoiogroup_comic);
        rbfollow = (RadioButton) view.findViewById(R.id.rbfollow);
        rbhot = (RadioButton) view.findViewById(R.id.rbhot);
        initViewPagerView();
        fragmentComicPagerAdapter adapter = new fragmentComicPagerAdapter(list,getActivity());
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        radoiogroup_comic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbfollow:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rbhot:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radoiogroup_comic.check(R.id.rbfollow);
                        break;
                    case 1:
                        radoiogroup_comic.check(R.id.rbhot);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
