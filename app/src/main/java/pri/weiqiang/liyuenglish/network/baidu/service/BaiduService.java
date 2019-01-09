package pri.weiqiang.liyuenglish.network.baidu.service;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.mvp.bean.BaiduTranslateBean;


public interface BaiduService {

    interface TranslateService {

        void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable);

    }


}
