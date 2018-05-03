package com.example.andriod.tablayoutproject;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.andriod.tablayoutproject.adapter.MainPageAdapter;
import com.example.andriod.tablayoutproject.ann.TabTypeAnn;
import com.example.andriod.tablayoutproject.entity.TabItemInfo;
import com.example.andriod.tablayoutproject.view.NoScrollableViewPager;
import com.example.andriod.tablayoutproject.view.frag.CartFragment;
import com.example.andriod.tablayoutproject.view.frag.FoundFragment;
import com.example.andriod.tablayoutproject.view.frag.GoodsFragment;
import com.example.andriod.tablayoutproject.view.frag.HomeFragment;
import com.example.andriod.tablayoutproject.view.frag.MeFragment;

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
    // 测试数据
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
        // TODO: 2018/5/3 从后台获取数据
        mTabItemInfoList = new ArrayList<>();
        mTabItemInfoList.add(new TabItemInfo(TabTypeAnn.HOME, mStringArray[0], mIconId[0], HomeFragment.class));
        mTabItemInfoList.add(new TabItemInfo(TabTypeAnn.GOODS, mStringArray[1], mIconId[1], GoodsFragment.class));
        mTabItemInfoList.add(new TabItemInfo(TabTypeAnn.FOUND, mStringArray[2], mIconId[2], FoundFragment.class));
        mTabItemInfoList.add(new TabItemInfo(TabTypeAnn.SHOP_CART, mStringArray[3], mIconId[3], CartFragment.class));
        mTabItemInfoList.add(new TabItemInfo(TabTypeAnn.ME, mStringArray[4], mIconId[4], MeFragment.class));
    }

    private void initView() {
        mMainPageAdapter = new MainPageAdapter(this, getSupportFragmentManager(), mTabItemInfoList);
        mViewPager.setAdapter(mMainPageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0, size = mTabLayout.getTabCount(); i < size; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (null != tab) {
                View tabView = mMainPageAdapter.getTabView(i, mTabItemInfoList.get(i).getmTabType());
                getCartNum((TextView) tabView.findViewById(R.id.tab_cart_tv));
                tab.setCustomView(tabView);
                mMainPageAdapter.setTabViewState(tab, tab.isSelected());
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mMainPageAdapter.setTabViewState(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mMainPageAdapter.setTabViewState(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * 异模拟步获取购物车数量显示在UI
     * @param tabView 购物车对应的tabview
     */
    private void getCartNum(final TextView tabView) {
        // 模拟异步获取--实际中可以使用Rx+网络通信框架实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isEnd = false;
                int cartNum = 0;
                for (; isEnd == false;) {
                    if (cartNum > 15) {
                        isEnd = true;
                        return;
                    }
                    cartNum++;
                    SystemClock.sleep(3000L);
                    final int finalCartNum = cartNum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalCartNum != 0) {
                                tabView.setText(String.valueOf(finalCartNum));
                            }
                        }
                    });
                }
            }
        }).start();
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
