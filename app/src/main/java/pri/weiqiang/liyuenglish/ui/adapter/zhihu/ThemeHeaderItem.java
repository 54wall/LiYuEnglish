package pri.weiqiang.liyuenglish.ui.adapter.zhihu;

import pri.weiqiang.liyuenglish.mvp.bean.zhihu.DisplaybleItem;

/**
 * Created by Administrator on 2017/1/6.
 */

class ThemeHeaderItem implements DisplaybleItem {
    private String image;
    private String description;

    public ThemeHeaderItem(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
