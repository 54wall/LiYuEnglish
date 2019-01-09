package pri.weiqiang.liyuenglish.mvp.view;

import android.content.DialogInterface;

import java.util.List;

import pri.weiqiang.liyuenglish.mvp.bean.Book;
import pri.weiqiang.liyuenglish.mvp.bean.LessonFav;
import pri.weiqiang.liyuenglish.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyuenglish.mvp.bean.Word;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.StoryContentEntity;

public interface BaseView<T> {

    void setData(List<T> data);

    interface MainActivityView {

        void openDrawer();

        void closeDrawer();

        void switchWords(String lesson, Boolean isExpandable);

        void switchZhihu();

        void switchNewsAPI();

        void updateNewsAPI(String category);

        void switchLessons();

        void switchFavLesson(Boolean isExpandable);

        void switchFavWord(String lessonId, Boolean isExpandable);

        void expandWords();

        void switchTranslate();

        void showSnackBar(int id);

        void switchAbout();

        void switchSetting();

        void showAlertDialog(int titleId, int messageId,
                             int positiveTextId, DialogInterface.OnClickListener positiveButtonListener,
                             int negativeTextId, DialogInterface.OnClickListener negativeButtonListener);
    }


    interface WordsFragmentView extends BaseView<Word> {

        void setRecyclerView();

    }

    interface ZhihuFragmentView {

        <T> void refreshHomeList(T t);

        void loadBeforeDaily(BeforeDailyEntity beforeDailyEntity);

    }

    interface NewsAPIFragmentView {

        <T> void refreshNews(T t);

        <T> void loadNewsBefore(T t);

    }

    interface ArticleDetailActivityView {
        void showContent(StoryContentEntity storyContentEntity);
    }

    interface FavWordsFragmentView extends BaseView<Word> {

        void setRecyclerView();


    }

    interface FavLessonFragmentView extends BaseView<LessonFav> {

        void setRecyclerView();
    }

    interface LessonsFragmentView extends BaseView<Book> {

        void initView();

    }

    interface TranslateFragmentView {
        void showMsg(String msg);

        void showMsg(int msg);

        String getSrcText();

        void setSrcEditText(String text);

        String getDstText();

        void setDstTextView(String text);

        void setFromSpinner(List<TranslateSpinnerItem> list);

        void setToSpinner(List<TranslateSpinnerItem> list);
    }


}
