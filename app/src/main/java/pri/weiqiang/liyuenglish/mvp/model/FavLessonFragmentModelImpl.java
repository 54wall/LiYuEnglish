package pri.weiqiang.liyuenglish.mvp.model;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.comparator.LessonFavComporator;
import pri.weiqiang.liyuenglish.manager.DBManager;
import pri.weiqiang.liyuenglish.mvp.bean.LessonFav;


public class FavLessonFragmentModelImpl implements BaseModel.FavLessonFragmentModel {

    private String TAG = FavLessonFragmentModelImpl.class.getSimpleName();

    @Override
    public void getData(Consumer<List<LessonFav>> consumer, Consumer<Throwable> throwable) {
        Observable.create(new ObservableOnSubscribe<List<LessonFav>>() {
            @Override
            public void subscribe(ObservableEmitter<List<LessonFav>> emitter) throws Exception {
                List<LessonFav> list;
                list = DBManager.getInstance().getFavLesson();
                if (list == null) {
                    Log.e(TAG, "list == null");
//                    emitter.onError(new Exception());//经常报这个错误io.reactivex.exceptions.OnErrorNotImplementedException
                } else {
                    //按照生词数量对课程排序
                    Collections.sort(list, new LessonFavComporator());
                    emitter.onNext(list);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable);
    }
}