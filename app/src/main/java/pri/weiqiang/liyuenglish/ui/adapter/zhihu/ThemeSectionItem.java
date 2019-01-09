package pri.weiqiang.liyuenglish.ui.adapter.zhihu;

import java.util.List;

import pri.weiqiang.liyuenglish.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.EditorsEntity;

/**
 * Created by Administrator on 2017/1/6.
 */

class ThemeSectionItem implements DisplaybleItem {

    private List<EditorsEntity> mEditors;

    public ThemeSectionItem(List<EditorsEntity> editors) {
        mEditors = editors;
    }

    public List<EditorsEntity> getEditors() {
        return mEditors;
    }

    public void setEditors(List<EditorsEntity> editors) {
        mEditors = editors;
    }
}
