package com.example.xiaowai.cartoonpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaowai.cartoonpro.R;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/3/16
 */

public class fragmentDiscoverSortAllAdvice extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_discover_sort_all_advice,null);
        return view;
    }
}
