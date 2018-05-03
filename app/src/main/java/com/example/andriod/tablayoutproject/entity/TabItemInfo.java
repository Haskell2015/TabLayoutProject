package com.example.andriod.tablayoutproject.entity;

import android.support.v4.app.Fragment;

/**
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class TabItemInfo {
    /**
     * tab类型<br></br>
     * <li>1:首页</li>
     * <li>2:分类</li>
     * <li>3:发现</li>
     * <li>4:购物车</li>
     * <li>5:会员</li>
     */
    private int mTabType;
    private String mTitle;
    private int[] mIconResId;
    private Class<? extends Fragment> mFragment;

    public TabItemInfo(int tabType,String mTitle, int[] mIconResId, Class<? extends Fragment> mFragment) {
        this.mTabType=tabType;
        this.mTitle = mTitle;
        this.mIconResId = mIconResId;
        this.mFragment = mFragment;
    }

    public int getmTabType() {
        return mTabType;
    }

    public void setmTabType(int mTabType) {
        this.mTabType = mTabType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int[] getmIconResId() {
        return mIconResId;
    }

    public void setmIconResId(int[] mIconResId) {
        this.mIconResId = mIconResId;
    }

    public Class<? extends Fragment> getmFragment() {
        return mFragment;
    }

    public void setmFragment(Class<? extends Fragment> mFragment) {
        this.mFragment = mFragment;
    }
}
