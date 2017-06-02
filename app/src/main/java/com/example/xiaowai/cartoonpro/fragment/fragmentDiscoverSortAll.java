package com.example.xiaowai.cartoonpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xiaowai.cartoonpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/16
 */

public class fragmentDiscoverSortAll extends Fragment{

    private View view;
    private FragmentManager manager;
    private List<Fragment> sortalllist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover_sort_all,null);
        findAllView();
        return view;
    }

    private void findAllView(){
        initFragment();
        RadioGroup title_rgthree= (RadioGroup) view.findViewById(R.id.discover_sort_title_rgthree);
        manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_discover_sort_all_rela,sortalllist.get(0),"advice").commit();
        title_rgthree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.discover_sort_title_rbadvice:
                        manager.beginTransaction().replace(R.id.fragment_discover_sort_all_rela,sortalllist.get(0),"advice").commit();
                        break;
                    case R.id.discover_sort_title_rbhot:
                        manager.beginTransaction().replace(R.id.fragment_discover_sort_all_rela,sortalllist.get(1),"hot").commit();
                        break;
                    case R.id.discover_sort_title_rblastest:
                        manager.beginTransaction().replace(R.id.fragment_discover_sort_all_rela,sortalllist.get(2),"lastest").commit();
                        break;
                }
            }
        });
    }

    private void initFragment() {
        for (int i = 0; i < 3; i++) {
            fragmentDiscoverSortAllAdvice sortAll = new fragmentDiscoverSortAllAdvice();
            Bundle bundle = new Bundle();
            bundle.putSerializable("tabIndex", i);
            sortAll.setArguments(bundle);
            sortalllist.add(sortAll);
        }
    }
}
