package pri.weiqiang.liyuenglish.network.youdao.service;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.mvp.bean.YoudaoTranslateBean;


interface YoudaoService {

    interface YoudaoTranslateService {

        void translate(String q, Consumer<YoudaoTranslateBean> consumer);
    }

}
