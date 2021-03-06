package pri.weiqiang.liyuenglish.mvp.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyuenglish.network.zhihu.Networks;


public class ArticleDetailActivityModelImpl implements BaseModel.ArticleDetailActivityModel {


    @Override
    public void getContent(Consumer<StoryContentEntity> consumer, Consumer<Throwable> throwable, int id) {
        Networks.getInstance().getCommonApi().getStoryContent(id)//产生被观察者
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, throwable);
    }
}
