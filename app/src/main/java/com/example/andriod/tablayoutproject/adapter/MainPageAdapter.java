package com.example.andriod.tablayoutproject.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andriod.tablayoutproject.R;
import com.example.andriod.tablayoutproject.ann.TabTypeAnn;
import com.example.andriod.tablayoutproject.entity.TabItemInfo;

import java.util.List;

/**
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    private static final String TAG = MainPageAdapter.class.getSimpleName();
    private List<TabItemInfo> mTabItemInfoList;
    private Context mContext;

    public MainPageAdapter(Context context, FragmentManager fm, List<TabItemInfo> tabItemInfoList) {
        super(fm);
        this.mContext = context;
        this.mTabItemInfoList = tabItemInfoList;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return mTabItemInfoList.get(position).getmFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return null != mTabItemInfoList ? mTabItemInfoList.size() : 0;
    }

    /**
     *
     * @param position tab选中的位置
     * @param tabType tab加载的fragment类型
     * @return 当前tab的视图
     */
    public View getTabView(int position, int tabType) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.tab_item_layout, null);
         TextView tvTitle = (TextView) tabView.findViewById(R.id.tab_title_tv);
        // ImageView ivIcon = (ImageView) tabView.findViewById(R.id.tab_icon_iv);

         tvTitle.setText(mTabItemInfoList.get(position).getmTitle());
        // ivIcon.setImageResource(mTabItemInfoList.get(position).getmIconResId()[0]);

        if (tabType == TabTypeAnn.SHOP_CART) {
            TextView tvCartNum = (TextView) tabView.findViewById(R.id.tab_cart_tv);
            tvCartNum.setVisibility(View.VISIBLE);
        }

        return tabView;
    }

    public void setTabViewState(TabLayout.Tab tab, boolean isTabSelected) {
        View tabCustomView = tab.getCustomView();
        TextView tvTitleInTab = (TextView) tabCustomView.findViewById(R.id.tab_title_tv);
        ImageView ivIconInTab = (ImageView) tabCustomView.findViewById(R.id.tab_icon_iv);

        tvTitleInTab.setTextColor(mContext.getResources()
            .getColor(isTabSelected ? R.color.tab_text_color_selected : R.color.tab_text_color_normal));

        int[] iconResIds = mTabItemInfoList.get(tab.getPosition()).getmIconResId();
        ivIconInTab.setImageResource(isTabSelected ? iconResIds[1] : iconResIds[0]);
    }
}
