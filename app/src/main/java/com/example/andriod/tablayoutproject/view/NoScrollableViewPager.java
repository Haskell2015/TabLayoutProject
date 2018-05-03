package com.example.andriod.tablayoutproject.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可设置是否滑动的ViewPager
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class NoScrollableViewPager extends ViewPager {
    /**
     * 默认不可滑动
     */
    private static final boolean DEFAULT_SCROLLABLE_SETTING = false;
    private boolean isScrollable = DEFAULT_SCROLLABLE_SETTING;

    public NoScrollableViewPager(Context context) {
        super(context);
    }

    public NoScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);
    }

    /**
     * 默认不可滑动，外部可通过该方法改成可滑动
     * @param scrollable 是否可滑动
     */
    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }
}
