package pri.weiqiang.liyuenglish.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.MyApplication;
import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyuenglish.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyuenglish.network.baidu.service.BaiduService;
import pri.weiqiang.liyuenglish.network.baidu.service.BaiduTranslateServiceImpl;
import pri.weiqiang.liyuenglish.utils.ResourceUtils;


public class TranslateFragmentModelImpl implements BaseModel.TranslateFragmentModel {

    private BaiduService.TranslateService service;

    public TranslateFragmentModelImpl() {
        super();
        service = new BaiduTranslateServiceImpl();
    }

    @Override
    public List<TranslateSpinnerItem> getFromList() {

        List<TranslateSpinnerItem> fromList = new ArrayList<>();
        fromList.add(new TranslateSpinnerItem(0, ResourceUtils.getString(MyApplication.getInstance(), R.string.auto_check), false));
        fromList.add(new TranslateSpinnerItem(R.drawable.china_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.chinese), true));
        fromList.add(new TranslateSpinnerItem(R.drawable.kingdom_united_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.english), true));
        fromList.add(new TranslateSpinnerItem(R.drawable.japan_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.japanese), true));

        return fromList;
    }

    @Override
    public List<TranslateSpinnerItem> getToList() {

        List<TranslateSpinnerItem> toList = new ArrayList<>();
        toList.add(new TranslateSpinnerItem(R.drawable.china_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.chinese), true));
        toList.add(new TranslateSpinnerItem(R.drawable.kingdom_united_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.english), true));
        toList.add(new TranslateSpinnerItem(R.drawable.japan_icon_64, ResourceUtils.getString(MyApplication.getInstance(), R.string.japanese), true));

        return toList;
    }

    @Override
    public void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable) {

        service.translate(q, from, to, consumer, throwable);

    }


}
