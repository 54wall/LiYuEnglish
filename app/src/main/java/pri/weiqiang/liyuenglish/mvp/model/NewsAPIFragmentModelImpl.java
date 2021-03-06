package pri.weiqiang.liyuenglish.mvp.model;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyuenglish.network.newsapi.NewsApiNetworks;


public class NewsAPIFragmentModelImpl implements BaseModel.NewsAPIFragmentModel {
    private String TAG = NewsAPIFragmentModelImpl.class.getSimpleName();

    @Override
    public void getHeadlinesByCountry(Consumer<NewsResponse> consumer, Consumer<Throwable> throwble, String country, String from, String to, String category, String pageSize, String apiKey) {
        Log.e(TAG, "getLatestNews");
        NewsApiNetworks.getInstance().getCommonApi().getHeadlinesByCountry(country, from, to, category, pageSize, apiKey)
//                .getLatestNews()//产生被观察者
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwble);
    }
}
