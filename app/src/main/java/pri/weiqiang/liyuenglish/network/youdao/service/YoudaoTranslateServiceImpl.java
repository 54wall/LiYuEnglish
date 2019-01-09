package pri.weiqiang.liyuenglish.network.youdao.service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.mvp.bean.YoudaoTranslateBean;
import pri.weiqiang.liyuenglish.network.youdao.YoudaoTranslateApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class YoudaoTranslateServiceImpl implements YoudaoService.YoudaoTranslateService {

    private static Retrofit instance = null;

    private static synchronized Retrofit getInstance() {

        if (instance == null) {

            instance = new Retrofit.Builder()
                    .baseUrl(Constants.YOUDAO_TRANSLATE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return instance;
    }


    @Override
    public void translate(String q, Consumer<YoudaoTranslateBean> consumer) {

        getInstance().create(YoudaoTranslateApi.class)
                .request(Constants.YOUDAO_TRANSLATE_KEYFROM, Constants.YOUDAO_TRANSLATE_APIKEY, YoudaoTranslateApi.TYPE,
                        YoudaoTranslateApi.DOCTYPE_JSON, YoudaoTranslateApi.VERSION, q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);


    }
}
