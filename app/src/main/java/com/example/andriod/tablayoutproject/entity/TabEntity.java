package com.example.andriod.tablayoutproject.entity;

/**
 * 后续从后台获取的数据Bean
 * @author jiangqq
 * @date 2018/5/3
 * Email:3350730342@qq.com
 */
public class TabEntity {
    /*{
    "title": "首页",
        "iconUrl": {
    "normalIconUrl": "正常URL",
            "selectedIconUrl": "选中URL"
    },
    "tabType": 1
    },
    {
        "title": "分类",
            "iconUrl": {
        "normalIconUrl": "正常URL",
                "selectedIconUrl": "选中URL"
    },
        "tabType": 2
    }
    ]*/
    /**
     * title : 首页
     * iconUrl : {"normalIconUrl":"正常URL","selectedIconUrl":"选中URL"}
     * tabType : 1
     */

    private String title;
    private IconUrlBean iconUrl;
    /**
     * tab的类型<br></br>
     * @see com.example.andriod.tablayoutproject.ann.TabTypeAnn
     */
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
         * normalIconUrl : 正常URL
         * selectedIconUrl : 选中URL
         */

        private String normalIconUrl;
        private String selectedIconUrl;

        public String getNormalIconUrl() {
            return normalIconUrl;
        }

        public void setNormalIconUrl(String normalIconUrl) {
            this.normalIconUrl = normalIconUrl;
        }

        public String getSelectedIconUrl() {
            return selectedIconUrl;
        }

        public void setSelectedIconUrl(String selectedIconUrl) {
            this.selectedIconUrl = selectedIconUrl;
        }
    }
}
