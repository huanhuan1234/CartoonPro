package com.example.xiaowai.cartoonpro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.activity.HotFollowActivity;
import com.example.xiaowai.cartoonpro.adapter.fragmentSocialPagerAdapter;
import com.example.xiaowai.cartoonpro.adapter.fragmentSocialSquarePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/15
 */

public class fragmentSocial  extends Fragment{

    private View view;
    private List<View> list;
    private ViewPager fragment_social_vp;
    private RadioGroup radoiogroup_social;
    private View squareView;
    private View focusView;
    private List<View> squarelist;
    private ViewPager social_square_vp;
    private RadioGroup square_rg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_social,null);
        findView();
        return view;
    }
    private void initView(){
        list = new ArrayList<View>();
        focusView = View.inflate(getActivity(), R.layout.fragment_comic_layout_follow,null);
        squareView = View.inflate(getActivity(), R.layout.fragment_social_square,null);
        findFocusView();
        list.add(focusView);
        list.add(squareView);
        findSquareView();
    }

    private void findFocusView() {
        ImageView imgfollow= (ImageView) focusView.findViewById(R.id.image_follow);
        ImageView imgfollowtext= (ImageView) focusView.findViewById(R.id.image_follow_text);
        ImageView imgfollowtextsocial= (ImageView) focusView.findViewById(R.id.image_follow_text_discover);
        imgfollowtext.setVisibility(View.GONE);
        imgfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HotFollowActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        if(flag){
            imgfollow.setVisibility(View.GONE);
            imgfollowtextsocial.setVisibility(View.GONE);
        }
    }

    private void initSquareView(){
        squarelist = new ArrayList<View>();
        View squarehot=View.inflate(getActivity(),R.layout.fragment_social_square_hot,null);
        View squarelastest=View.inflate(getActivity(),R.layout.fragment_social_square_lastest,null);
        squarelist.add(squarehot);
        squarelist.add(squarelastest);
    }

    private void findSquareView(){
        social_square_vp = (ViewPager) squareView.findViewById(R.id.social_square_vp);
     //   RadioButton rbhot= (RadioButton) squareView.findViewById(R.id.social_square_rg_rbhot);
     //   RadioButton rblastest= (RadioButton) squareView.findViewById(R.id.social_square_rg_rblastest);
        square_rg = (RadioGroup) squareView.findViewById(R.id.social_square_rg);
        initSquareView();
        fragmentSocialSquarePagerAdapter adapter = new fragmentSocialSquarePagerAdapter(squarelist, getActivity());
        social_square_vp.setAdapter(adapter);
        square_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.social_square_rg_rbhot:
                        social_square_vp.setCurrentItem(0);
                        break;
                    case R.id.social_square_rg_rblastest:
                        social_square_vp.setCurrentItem(1);
                        break;
                }

            }
        });

        squareVpChanges();
    }

    private void squareVpChanges(){
        social_square_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        square_rg.check(R.id.social_square_rg_rbhot);
                        break;
                    case 1:
                        square_rg.check(R.id.social_square_rg_rblastest);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findView(){
        radoiogroup_social = (RadioGroup) view.findViewById(R.id.radoiogroup_social);
//        RadioButton rbfocus= (RadioButton) view.findViewById(R.id.rbfocus);
//        RadioButton rbSquare= (RadioButton) view.findViewById(R.id.rbSquare);
        fragment_social_vp = (ViewPager) view.findViewById(R.id.fragment_social_vp);
        initView();
        fragmentSocialPagerAdapter adapter = new fragmentSocialPagerAdapter(list,getActivity());
        fragment_social_vp.setAdapter(adapter);
        radoiogroup_social.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbfocus:
                        fragment_social_vp.setCurrentItem(0);
                        break;
                    case R.id.rbSquare:
                        fragment_social_vp.setCurrentItem(1);
                        break;
                }
            }
        });

        fragment_social_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radoiogroup_social.check(R.id.rbfocus);
                        break;
                    case 1:
                        radoiogroup_social.check(R.id.rbSquare);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
