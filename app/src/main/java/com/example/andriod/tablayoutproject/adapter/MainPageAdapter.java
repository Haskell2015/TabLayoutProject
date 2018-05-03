package com.example.andriod.tablayoutproject.adapter;

import java.util.List;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andriod.tablayoutproject.R;
import com.example.andriod.tablayoutproject.ann.TabTypeAnn;
import com.example.andriod.tablayoutproject.entity.TabItemInfo;

/**
 * 主页面的ViewPagerAdapter
 *
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    private static final String TAG = MainPageAdapter.class.getSimpleName();
    private int[][] mDefaultTabIconResIds;
    private List<TabItemInfo> mTabItemInfoList;
    private Context mContext;

    // TODO: 2018/5/3 默认tab图片的传递需要根据实际进行优化--后台返回的顺序和本地预设的默认图片顺序可能会不一样，需要处理
    public MainPageAdapter(Context context, FragmentManager fm, List<TabItemInfo> tabItemInfoList,
        int[][] defaultTabIconResIds) {
        super(fm);
        this.mContext = context;
        this.mTabItemInfoList = tabItemInfoList;
        this.mDefaultTabIconResIds = defaultTabIconResIds;
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
     * 获取对应tab的view
     *
     * @param position tab选中的位置
//     * @param tabType  tab加载的fragment类型
     * @return 当前tab的视图
     */
    public View getTabView(int position) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.tab_item_layout, null);
        TextView tvTitle = (TextView) tabView.findViewById(R.id.tab_title_tv);
        tvTitle.setText(mTabItemInfoList.get(position).getmTitle());
        return tabView;
    }

    /**
     * 用于设置tab选中和非选中的显示效果--主要是图标和文字的颜色
     *
     * @param tab           当前选中的tab
     * @param isTabSelected 当前tab是否为选中状态
     */
    public void setTabViewState(TabLayout.Tab tab, boolean isTabSelected) {
        View tabCustomView = tab.getCustomView();

        if (null == tabCustomView) {
            return;
        }

        TextView tvTitleInTab = (TextView) tabCustomView.findViewById(R.id.tab_title_tv);
        ImageView ivIconInTab = (ImageView) tabCustomView.findViewById(R.id.tab_icon_iv);

        tvTitleInTab.setTextColor(mContext.getResources()
            .getColor(isTabSelected ? R.color.tab_text_color_selected : R.color.tab_text_color_normal));

        int currentTabPosition = tab.getPosition();
        int[] iconResIds = mTabItemInfoList.get(currentTabPosition).getmIconResId();
        Glide.with(mContext)
            .load(isTabSelected ? iconResIds[1] : iconResIds[0])
            .error(isTabSelected ? mDefaultTabIconResIds[currentTabPosition][1]
                : mDefaultTabIconResIds[currentTabPosition][0])
            .into(ivIconInTab);
    }
}
