package pri.weiqiang.liyuenglish.mvp.presenter;

import pri.weiqiang.liyuenglish.rxbus.event.EventContainer;

public abstract class BasePresenter<T> {

    final T view;

    BasePresenter(T view) {
        this.view = view;
    }

    public interface MainActivityPresenter {

        void initMainActivity();

        void onNavigationItemSelected(int id);

        void onBusEventInteraction(EventContainer eventContainer);

        void onMenuItemSelected(int id);

    }


    public interface WordsFragmentPresenter {

        void initWordsFragment();

        void randomList();

    }

    public interface ZhihuFragmentPresenter {

        void initZhihuFragment();

        void getBeforeDaily(String date);

    }

    public interface NewsAPIFragmentPresenter {

        void getNews(String country, String from, String to, String category, String pageSize, String apiKey);

        void getNewsBefore(String country, String from, String to, String category, String pageSize, String apiKey);


    }

    public interface ArticleDetailActivityPresenter {

        void showContent(int id);

    }

    public interface FavWordsFragmentPresenter {

        void initFavWordsFragment();

        void randomList();

    }

    public interface FavLessonFragmentPresenter {

        void initFavLessonFragment();

    }

    public interface LessonsFragmentPresenter {

        void initLessonsFragment();

    }


    public interface TranslateFragmentPresenter {

        void initTranslateFragment();

        void checkFromLanguate(int from);

        void checkToLanguage(int to);

        void checkImageViewClick(int id);

        void doTranslate();

    }
}
