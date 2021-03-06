package pri.weiqiang.liyuenglish.mvp.model;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.manager.DBManager;
import pri.weiqiang.liyuenglish.mvp.bean.Word;


public class FavWordsFragmentModelImpl implements BaseModel.FavWordsFragmentModel {

    private String TAG = FavWordsFragmentModelImpl.class.getSimpleName();

    @Override
    public void getData(Consumer<List<Word>> consumer, Consumer<Throwable> throwable, final String lessonId) {
        Observable.create(new ObservableOnSubscribe<List<Word>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Word>> emitter) throws Exception {
                List<Word> list;
                list = DBManager.getInstance().getFav(lessonId);
                if (list == null) {
                    Log.e(TAG, "list == null");
//                    emitter.onError(new Exception());//经常报这个错误io.reactivex.exceptions.OnErrorNotImplementedException
                } else {
                    emitter.onNext(list);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable);
    }
}
