package com.example.andriod.tablayoutproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andriod.tablayoutproject.R;

/**
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.home_fragment_layout,null);
        return root;
    }
}
