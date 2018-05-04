package com.example.andriod.tablayoutproject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.example.andriod.tablayoutproject.adapter.MainPageAdapter;
import com.example.andriod.tablayoutproject.ann.TabTypeAnn;
import com.example.andriod.tablayoutproject.entity.TabEntity;
import com.example.andriod.tablayoutproject.entity.TabItemInfo;
import com.example.andriod.tablayoutproject.view.NoScrollableViewPager;
import com.example.andriod.tablayoutproject.view.frag.CartFragment;
import com.example.andriod.tablayoutproject.view.frag.FoundFragment;
import com.example.andriod.tablayoutproject.view.frag.GoodsFragment;
import com.example.andriod.tablayoutproject.view.frag.HomeFragment;
import com.example.andriod.tablayoutproject.view.frag.MeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Andriod
 */
public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TEST_TAB_ITEM_JSON_DATA =
        "[{\"title\": \"首页\",\"iconUrl\": {\"normalUrl\": \"正常的URL\",\"selectedIconUrl\": \"选中URL\"},\"tabType\": 1},{\"title\": \"分类\",\"iconUrl\": {\"normalUrl\": \"正常的URL\",\"selectedIconUrl\": \"选中URL\"},\"tabType\": 2},{\"title\": \"发现\",\"iconUrl\": {\"normalUrl\": \"正常的URL\",\"selectedIconUrl\": \"选中URL\"},\"tabType\": 3},{\"title\": \"购物车\",\"iconUrl\": {\"normalUrl\": \"正常的URL\",\"selectedIconUrl\": \"选中URL\"},\"tabType\": 4},{\"title\": \"会员\",\"iconUrl\": {\"normalUrl\": \"正常的URL\",\"selectedIconUrl\": \"选中URL\"},\"tabType\": 5}]";
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
    private SparseArray mTabFragmentSparseArray;
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
        // 初始化tab对应的Fragment
        // mTabFragmentSparseArray=new SparseArray();
        // mTabFragmentSparseArray.put(TabTypeAnn.HOME,HomeFragment.class);
        // mTabFragmentSparseArray.put(TabTypeAnn.GOODS,GoodsFragment.class);
        // mTabFragmentSparseArray.put(TabTypeAnn.FOUND,FoundFragment.class);
        // mTabFragmentSparseArray.put(TabTypeAnn.SHOP_CART,CartFragment.class);
        // mTabFragmentSparseArray.put(TabTypeAnn.ME,MeFragment.class);
        // 初始化加载tab所需的数据
        mTabItemInfoList = new ArrayList<>();
        // TODO: 2018/5/4 模拟后台返回json
        String tabItemInfo = loadDataFromServer();

        Type jsonType = new TypeToken<List<TabEntity>>() {
        }.getType();
        Gson tabJson = new Gson();
        List<TabEntity> tabEntityList = tabJson.fromJson(tabItemInfo, jsonType);
        for (int i = 0, size = tabEntityList.size(); i < size; i++) {
            TabEntity tabEntity = tabEntityList.get(i);
            TabItemInfo itemInfo = new TabItemInfo();
            itemInfo.setmTitle(tabEntity.getTitle());
            itemInfo.setmIconUrlBean(tabEntity.getIconUrl());
            itemInfo.setmTabType(tabEntity.getTabType());
            // itemInfo.setmFragment(mTabFragmentSparseArray.get(tabEntity.getTabType()));
            // TODO: 2018/5/4 考虑用Moshi
            switch (tabEntity.getTabType()) {
                case TabTypeAnn.HOME: {
                    itemInfo.setmFragment(HomeFragment.class);
                    break;
                }
                case TabTypeAnn.GOODS: {
                    itemInfo.setmFragment(GoodsFragment.class);
                    break;
                }
                case TabTypeAnn.FOUND: {
                    itemInfo.setmFragment(FoundFragment.class);
                    break;
                }
                case TabTypeAnn.SHOP_CART: {
                    itemInfo.setmFragment(CartFragment.class);
                    break;
                }
                case TabTypeAnn.ME: {
                    itemInfo.setmFragment(MeFragment.class);
                    break;
                }
                default:
                    break;
            }
            mTabItemInfoList.add(itemInfo);
        }

        // 从xml获取默认的一些配置
        // TypedArray typedArray = getResources().obtainTypedArray(R.array.tab_default);
        // 获取默认的title
        // String[] titleArray = getResources().getStringArray(typedArray.getResourceId(0, -1));
        // TypedArray typeArr = getResources().obtainTypedArray(R.array.tab_icon_default);
        // getResources().getIntArray(typedArray.getResourceId(1, -1));

    }

    private String loadDataFromServer() {
        return TEST_TAB_ITEM_JSON_DATA;
    }

    private void initView() {
        mMainPageAdapter = new MainPageAdapter(this, getSupportFragmentManager(), mTabItemInfoList, mIconId);
        mViewPager.setAdapter(mMainPageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0, size = mTabLayout.getTabCount(); i < size; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (null != tab) {
                View tabView = mMainPageAdapter.getTabView(i);
                if (mTabItemInfoList.get(i).getmTabType() == TabTypeAnn.SHOP_CART) {
                    getCartNum((TextView) tabView.findViewById(R.id.tab_cart_tv));
                }
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
                int cartNum = 0;
                for (;;) {
                    if (cartNum > 15) {
                        return;
                    }
                    cartNum++;
                    SystemClock.sleep(3000L);
                    final int finalCartNum = cartNum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalCartNum != 0) {
                                tabView.setVisibility(View.VISIBLE);
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
