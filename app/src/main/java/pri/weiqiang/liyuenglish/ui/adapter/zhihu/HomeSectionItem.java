package pri.weiqiang.liyuenglish.ui.adapter.zhihu;

import android.util.Log;

import java.util.Date;

import pri.weiqiang.liyuenglish.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyuenglish.utils.DateUtils;

/**
 * Created by Administrator on 2017/1/4.
 */

public class HomeSectionItem implements DisplaybleItem {

    private String TAG = HomeSectionItem.class.getSimpleName();
    private String strDate;
    private String formatDate;

    public HomeSectionItem(String strDate) {
        this.strDate = strDate;
    }

    public String getFormatDate() {
        if (strDate == null) {
            return null;
        }

        Date date = new Date();
        date = DateUtils.str2date(strDate, "yyyyMMdd");
        Log.e(TAG, "strDate " + strDate);
        Log.e(TAG, "date " + date);
        formatDate = DateUtils.date2str(date);
        Log.e(TAG, "formatDate " + formatDate);
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }
}
