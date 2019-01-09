package pri.weiqiang.liyuenglish.mvp.presenter;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.StoryContentEntity;
import pri.weiqiang.liyuenglish.mvp.model.ArticleDetailActivityModelImpl;
import pri.weiqiang.liyuenglish.mvp.model.BaseModel;
import pri.weiqiang.liyuenglish.mvp.view.BaseView;

/**
 * Created by weiqiang on 2018/4/10.
 */

public class ArticleDetailActivityPresenterImpl extends BasePresenter<BaseView.ArticleDetailActivityView> implements BasePresenter.ArticleDetailActivityPresenter {

    private final String TAG = getClass().getSimpleName();

    private BaseModel.ArticleDetailActivityModel model;

    public ArticleDetailActivityPresenterImpl(BaseView.ArticleDetailActivityView view) {
        super(view);
        model = new ArticleDetailActivityModelImpl();
    }

    @Override
    public void showContent(int id) {
        model.getContent(new Consumer<StoryContentEntity>() {
            @Override
            public void accept(StoryContentEntity storyContentEntity) {
                view.showContent(storyContentEntity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "showContent-throwable:" + throwable.toString());
            }
        }, id);
    }
}
