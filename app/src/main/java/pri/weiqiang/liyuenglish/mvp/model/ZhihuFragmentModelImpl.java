package pri.weiqiang.liyuenglish.mvp.model;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyuenglish.network.zhihu.Networks;


public class ZhihuFragmentModelImpl implements BaseModel.ZhihuFragmentModel {
    private String TAG = ZhihuFragmentModelImpl.class.getSimpleName();

    @Override
    public void getLatestDaily(Consumer<LatestDailyEntity> consumer, Consumer<Throwable> throwble) {

        Networks.getInstance().getCommonApi().getLatestDaily()//产生被观察者
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwble)

        ;
    }

    @Override
    public void getBeforeDaily(Consumer<BeforeDailyEntity> consumer, Consumer<Throwable> throwble, String date) {
        Networks.getInstance().getCommonApi().getBeforeDaily(date)//产生被观察者
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, BeforeDailyEntity>() {
                    @Override
                    public BeforeDailyEntity apply(Throwable throwable) throws Exception {
                        Log.e(TAG, "BeforeNewsEntity onErrorReturn");
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwble);
    }
}
