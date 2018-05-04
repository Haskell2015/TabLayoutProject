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
     * @see com.example.andriod.tablayoutproject.ann.TabTypeAnn
     */
    private int mTabType;
    private String mTitle;
    private TabEntity.IconUrlBean mIconUrlBean;
    private Class<? extends Fragment> mFragment;

    public TabItemInfo() {
    }

    public TabEntity.IconUrlBean getmIconUrlBean() {
        return mIconUrlBean;
    }

    public void setmIconUrlBean(TabEntity.IconUrlBean mIconUrlBean) {
        this.mIconUrlBean = mIconUrlBean;
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

    public Class<? extends Fragment> getmFragment() {
        return mFragment;
    }

    public void setmFragment(Class<? extends Fragment> mFragment) {
        this.mFragment = mFragment;
    }
}
