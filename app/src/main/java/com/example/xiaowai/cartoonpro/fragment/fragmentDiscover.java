package com.example.xiaowai.cartoonpro.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.adapter.fragmentComicHotPagerAdapter;
import com.example.xiaowai.cartoonpro.adapter.fragmentDiscoverPagerAdapter;
import com.example.xiaowai.cartoonpro.adapter.fragmentDiscoverSortPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentDiscover extends Fragment {
    private FragmentManager manager;
    private View view;
    private RadioGroup rgsex;
    private RadioButton rbboy;
    private RadioButton rbgirl;
    private List<Fragment> sexlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover,null);
        findView();
        return view;
    }

    private void initFragmentData(){
                fragmentDiscoverBoy fdb = new fragmentDiscoverBoy();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("tabIndex", "boy");
                fdb.setArguments(bundle1);
                sexlist.add(fdb);
           fragmentDiscoverGirl fdg = new fragmentDiscoverGirl();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("tabIndex", "girl");
        fdg.setArguments(bundle2);
        sexlist.add(fdg);

    }

    private void findView(){
        sexlist=new ArrayList<>();
        initFragmentData();
        rgsex = (RadioGroup) view.findViewById(R.id.rgsex);
        rbboy = (RadioButton) view.findViewById(R.id.rbboy);
        rbgirl = (RadioButton) view.findViewById(R.id.rbgirl);
        manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout,sexlist.get(0),"boy").commit();
        rgsex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbboy:
                        rbgirl.setVisibility(View.VISIBLE);
                        rbboy.setVisibility(View.GONE);
                        manager.beginTransaction().replace(R.id.framelayout,sexlist.get(1),"girl").commit();
                        Toast.makeText(getActivity(),"切换为女生版",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbgirl:
                        rbgirl.setVisibility(View.GONE);
                        rbboy.setVisibility(View.VISIBLE);
                        manager.beginTransaction().replace(R.id.framelayout,sexlist.get(0),"boy").commit();
                        Toast.makeText(getActivity(),"切换为男生版",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}
