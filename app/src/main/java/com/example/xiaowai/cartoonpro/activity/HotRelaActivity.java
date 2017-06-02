package com.example.xiaowai.cartoonpro.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.xiaowai.cartoonpro.R;
import com.example.xiaowai.cartoonpro.Utils.ImageLoaderOptionsUtils;
import com.example.xiaowai.cartoonpro.adapter.HotTzPagerAdapter;
import com.example.xiaowai.cartoonpro.bean.FiveBean;
import com.example.xiaowai.cartoonpro.fragment.fragmentComicHotFive;
import com.example.xiaowai.cartoonpro.fragment.fragmentHotTzChooseSeries;
import com.example.xiaowai.cartoonpro.fragment.fragmentHotTzDetail;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HotRelaActivity extends AppCompatActivity {

    private Button btnreturn;
    private Button btnfollow;
    private TextView  btntype1;
    private TextView tv_title;
    private RelativeLayout tz_rela;
    private String duixiang;
    private FiveBean.DataBean.ComicsBean comicsBean;
    private ImageView img;
    private TextView bottom_rela_tv;
    private Button bottom_rela_isbegin;
    private RelativeLayout tz_rela_bottom;
    private int position;
    private int beforePosition;
    private ViewPager vp;
    private TabLayout tableLayout;
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> tablist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_rela2);
        findView();
        Intent intent = getIntent();
        duixiang = intent.getStringExtra("duixiang");
        position = intent.getIntExtra("position",-1);
        initControl();
    }

    private void setBtnText() {
        if(position==beforePosition){
            bottom_rela_isbegin.setText("继续阅读");
        }else{
            bottom_rela_isbegin.setText("开始阅读");
        }
    }

    private void initControl() {
        Gson gson=new Gson();
        comicsBean = gson.fromJson(duixiang, FiveBean.DataBean.ComicsBean.class);
        setBtnText();
        beforePosition=position;
        btntype1.setText(comicsBean.getLabel_text());
        tv_title.setText(comicsBean.getTopic().getTitle());
        bottom_rela_tv.setText(comicsBean.getTitle());
        tz_rela_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HotRelaActivity.this,HotWebViewActivity.class);
                i.putExtra("duixiang",duixiang);
                startActivity(i);
            }
        });
        ImageLoader.getInstance().displayImage(comicsBean.getTopic().getVertical_image_url(),img, ImageLoaderOptionsUtils.imageOptions(R.mipmap.ic_launcher));
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HotRelaActivity.this,HotFollowActivity.class);
                startActivity(i);
            }
        });
        initIndicator();

    }

    private void initIndicator() {
        if(fragmentList.size()==0){
            initFragmentList();
        }
        if(tablist.size()==0){
            initTabList();
        }
        HotTzPagerAdapter hotTzPagerAdapter = new HotTzPagerAdapter(getSupportFragmentManager(), fragmentList, tablist);
        vp.setAdapter(hotTzPagerAdapter);
        tableLayout.setupWithViewPager(vp);
    }

    private void initTabList() {
        tablist.add("详情");
        tablist.add("选集");
    }

    private void initFragmentList() {
        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        fragmentList.add(new fragmentHotTzDetail());
        fragmentList.add(new fragmentHotTzChooseSeries());
    }

    private void findView() {
        btnreturn = (Button) findViewById(R.id.tz_rela_btnreturn);
        btnfollow = (Button) findViewById(R.id.tz_rela_btnfollow);
        btntype1 = (TextView) findViewById(R.id.tz_rela_btntype1);
        tv_title = (TextView) findViewById(R.id.tz_rela_tv_title);
        img = (ImageView) findViewById(R.id.hot_rela_tz_image);
        bottom_rela_tv = (TextView) findViewById(R.id.tz_rela_bottom_rela_tv);
        bottom_rela_isbegin = (Button) findViewById(R.id.tz_rela_bottom_rela_isbegin);
        tz_rela_bottom = (RelativeLayout) findViewById(R.id.tz_rela_bottom_rela);
        vp = (ViewPager) findViewById(R.id.tz_vp);
        tableLayout = (TabLayout) findViewById(R.id.tz_tab);
    }

}
