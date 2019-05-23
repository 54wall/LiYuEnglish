package pri.weiqiang.liyuenglish.mvp.model;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyuenglish.mvp.bean.Book;
import pri.weiqiang.liyuenglish.mvp.bean.LessonFav;
import pri.weiqiang.liyuenglish.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyuenglish.mvp.bean.Word;
import pri.weiqiang.liyuenglish.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.StoryContentEntity;


public interface BaseModel<T> {


    List<T> getData();


    interface MainActivityModel {
    }

    interface WordsFragmentModel {
        void getData(Consumer<List<Word>> consumer, String lesson);
    }

    interface ZhihuFragmentModel {
        void getLatestDaily(Consumer<LatestDailyEntity> consumer, Consumer<Throwable> throwable);

        void getBeforeDaily(Consumer<BeforeDailyEntity> consumer, Consumer<Throwable> throwable, String date);
    }

    interface NewsAPIFragmentModel {
        void getHeadlinesByCountry(Consumer<NewsResponse> consumer, Consumer<Throwable> throwable, String country, String from, String to, String category, String pageSize, String apiKey);
    }


    interface ArticleDetailActivityModel {
        void getContent(Consumer<StoryContentEntity> consumer, Consumer<Throwable> throwable, int id);
    }

    interface FavWordsFragmentModel {
        void getData(Consumer<List<Word>> consumer, Consumer<Throwable> throwable, final String lessonId);

    }

    interface FavLessonFragmentModel {
        void getData(Consumer<List<LessonFav>> consumer, Consumer<Throwable> throwable);

    }

    interface LessonsFragmentModel {
        void getData(Consumer<List<Book>> consumer);
    }

    interface TranslateFragmentModel {

        List<TranslateSpinnerItem> getFromList();

        List<TranslateSpinnerItem> getToList();

        void translate(String q, String from, String to, Consumer<BaiduTranslateBean> consumer, Consumer<Throwable> throwable);

    }

}
