package com.example.andriod.tablayoutproject.entity;

/**
 * 后续从后台获取的数据Bean
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class TabEntity {
    /*[{"title": "首页","iconUrl": {"normalUrl": "正常的URL","selectedIconUrl": "选中URL"},"tabType": 1},{"title": "分类","iconUrl": {"normalUrl": "正常的URL","selectedIconUrl": "选中URL"},"tabType": 2},{"title": "发现","iconUrl": {"normalUrl": "正常的URL","selectedIconUrl": "选中URL"},"tabType": 3},{"title": "购物车","iconUrl": {"normalUrl": "正常的URL","selectedIconUrl": "选中URL"},"tabType": 4},{"title": "会员","iconUrl": {"normalUrl": "正常的URL","selectedIconUrl": "选中URL"},"tabType": 5}]*/
    /**
     * title : 首页
     * iconUrl : {"normalUrl":"正常的URL","selectedIconUrl":"选中URL"}
     * tabType : 1
     */

    private String title;
    private IconUrlBean iconUrl;
    private int tabType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IconUrlBean getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(IconUrlBean iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }

    public static class IconUrlBean {
        /**
         * normalUrl : 正常的URL
         * selectedIconUrl : 选中URL
         */

        private String normalUrl;
        private String selectedIconUrl;

        public String getNormalUrl() {
            return normalUrl;
        }

        public void setNormalUrl(String normalUrl) {
            this.normalUrl = normalUrl;
        }

        public String getSelectedIconUrl() {
            return selectedIconUrl;
        }

        public void setSelectedIconUrl(String selectedIconUrl) {
            this.selectedIconUrl = selectedIconUrl;
        }
    }
}
