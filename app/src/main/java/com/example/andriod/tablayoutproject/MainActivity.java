package com.example.andriod.tablayoutproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.andriod.tablayoutproject.adapter.MainPageAdapter;
import com.example.andriod.tablayoutproject.entity.TabItemInfo;
import com.example.andriod.tablayoutproject.view.FoundFragment;
import com.example.andriod.tablayoutproject.view.MeFragment;
import com.example.andriod.tablayoutproject.view.HomeFragment;
import com.example.andriod.tablayoutproject.view.CartFragment;
import com.example.andriod.tablayoutproject.view.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Andriod
 */
public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    static String[] mStringArray;
    static int[][] mIconId;
    static {
        mStringArray = new String[] {"首页", "分类", "发现", "购物车", "会员"};
        mIconId = new int[][] {{R.drawable.tab_home, R.drawable.tab_home_press},
            {R.drawable.tab_goods, R.drawable.tab_goods_press}, {R.drawable.tab_shops, R.drawable.tab_shops_press},
            {R.drawable.tab_shop_cart, R.drawable.tab_shop_cart_press}, {R.drawable.tab_me, R.drawable.tab_me_press}};
    }
    @BindView(R.id.main_vp)
    NoScrollableViewPager mViewPager;
    @BindView(R.id.main_tl)
    TabLayout mTabLayout;

    private List<TabItemInfo> mTabItemInfoList;
    private MainPageAdapter mMainPageAdapter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mUnbinder = ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mTabItemInfoList = new ArrayList<>();
        mTabItemInfoList.add(new TabItemInfo(1, mStringArray[0], mIconId[0], HomeFragment.class));
        mTabItemInfoList.add(new TabItemInfo(2, mStringArray[1], mIconId[1], GoodsFragment.class));
        mTabItemInfoList.add(new TabItemInfo(3, mStringArray[2], mIconId[2], FoundFragment.class));
        mTabItemInfoList.add(new TabItemInfo(4, mStringArray[3], mIconId[3], CartFragment.class));
        mTabItemInfoList.add(new TabItemInfo(5, mStringArray[4], mIconId[4], MeFragment.class));
    }

    private void initView() {
        mMainPageAdapter = new MainPageAdapter(this, getSupportFragmentManager(), mTabItemInfoList);
        mViewPager.setAdapter(mMainPageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0, size = mTabLayout.getTabCount(); i < size; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (null != tab) {
                View tabView = mMainPageAdapter.getTabView(i, mTabItemInfoList.get(i).getmTabType());
                tab.setCustomView(tabView);
                 mMainPageAdapter.setTabViewState(tab,tab.isSelected());
//                if (tab.isSelected()) {
//                    ((TextView) tabView.findViewById(R.id.tab_title_tv))
//                        .setTextColor(getResources().getColor(R.color.tab_text_color_selected));
//                }
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "tab.isSelected():" + tab.isSelected());
                Log.d(TAG, "onTabSelected");

                mMainPageAdapter.setTabViewState(tab,true);
//                 ((TextView) tab.getCustomView().findViewById(R.id.tab_title_tv))
//                 .setTextColor(getResources().getColor(R.color.tab_text_color_selected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "tab.isSelected():" + tab.isSelected());
                Log.d(TAG, "onTabUnselected");

                mMainPageAdapter.setTabViewState(tab,false);
//                 ((TextView) tab.getCustomView().findViewById(R.id.tab_title_tv))
//                 .setTextColor(getResources().getColor(R.color.tab_text_color_normal));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                Log.d(TAG, "tab.isSelected():" + tab.isSelected());
//                Log.d(TAG, "onTabReselected");
//                mMainPageAdapter.setTabViewState(tab);
            }
        });
    }

    private void setTab(int position) {
        mTabLayout.getTabAt(position).select();
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
