package com.example.andriod.tablayoutproject.ann;

import android.support.annotation.IntDef;

/**
 * Tab的类型
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
@IntDef({TabTypeAnn.HOME, TabTypeAnn.GOODS, TabTypeAnn.FOUND, TabTypeAnn.SHOP_CART, TabTypeAnn.ME})
public @interface TabTypeAnn {
    /**
     * 首页
     */
    int HOME = 1;
    /**
     * 分类
     */
    int GOODS = 2;
    /**
     * 发现
     */
    int FOUND = 3;
    /**
     * 购物车
     */
    int SHOP_CART = 4;
    /**
     * 会员
     */
    int ME = 5;
}
