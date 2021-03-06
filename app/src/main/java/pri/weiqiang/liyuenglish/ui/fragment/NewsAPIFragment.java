package pri.weiqiang.liyuenglish.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.mvp.bean.newsapi.DisplaybleNews;
import pri.weiqiang.liyuenglish.mvp.bean.newsapi.NewsResponse;
import pri.weiqiang.liyuenglish.mvp.presenter.BasePresenter;
import pri.weiqiang.liyuenglish.mvp.presenter.NewsAPIFragmentPresenterImpl;
import pri.weiqiang.liyuenglish.mvp.view.BaseView;
import pri.weiqiang.liyuenglish.ui.adapter.newsapi.NewsApiHYArticleListAdapter;
import pri.weiqiang.liyuenglish.ui.adapter.newsapi.SectionItem;
import pri.weiqiang.liyuenglish.utils.DateUtils;

/**
 * Created by weiqiang on 2018/12/11.
 */

public class NewsAPIFragment extends BaseFragment implements BaseView.NewsAPIFragmentView {

    private static final String TAG = NewsAPIFragment.class.getSimpleName();
    public BasePresenter.NewsAPIFragmentPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    private NewsApiHYArticleListAdapter mArticleListAdapter;
    private List<DisplaybleNews> mArticleList;
    private String mdate;
    private String country;
    private String category;


    public static NewsAPIFragment newInstance() {
        NewsAPIFragment fragment = new NewsAPIFragment();
        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_news_api;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        presenter = new NewsAPIFragmentPresenterImpl(this);
        mArticleList = new ArrayList<>();
        mArticleListAdapter = new NewsApiHYArticleListAdapter(getContext(), mArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mArticleListAdapter);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mdate = DateUtils.getCurDate();
                Log.e(TAG, "onRefresh()+mdate:" + mdate);
                country = Constants.NEWS_COUNTRY;
                category = Constants.NEWS_CATEGORY_TECHNOLOGY;
                presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e(TAG, "onScrollStateChanged-getChildCount:" + recyclerView.getLayoutManager().getChildCount());
                if (recyclerView.getLayoutManager().getChildCount() != 0) {
                    View lastchildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                    int lastChildBottomY = lastchildView.getBottom();
                    int recyclerBottomY = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                    int lastPosition = recyclerView.getLayoutManager().getPosition(lastchildView);

                    if (lastChildBottomY == recyclerBottomY && newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        mdate = DateUtils.getSpecifiedDayBefore(mdate);
                        Log.e(TAG, "前一天:" + mdate);
                        presenter.getNewsBefore(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
                    }
                }
            }
        });
    }

    @Override
    protected void doAction() {
        mdate = DateUtils.getCurDate();
        country = Constants.NEWS_COUNTRY;
        category = Constants.NEWS_CATEGORY_TECHNOLOGY;
        presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);

    }

    public void updateNewsAPI(String category) {
        Log.e(TAG, "updateNewsAPI category=" + category);
        presenter.getNews(country, mdate, mdate, category, Constants.API_NEWS_PAGE, Constants.API_NEWS_KEY);
    }


    @Override
    public <T> void refreshNews(T t) {
        mArticleList.clear();
        NewsResponse newsResponse = (NewsResponse) t;
        Log.e(TAG, "refreshNews mdate:" + mdate);
        mArticleList.add(new SectionItem(mdate));
        mArticleList.addAll(newsResponse.getArticles());
        mArticleListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public <T> void loadNewsBefore(T t) {
        Log.e(TAG, "loadNewsBefore mdate:" + mdate);
        NewsResponse newsResponse = (NewsResponse) t;
        mArticleList.add(new SectionItem(mdate));
        mArticleList.addAll(newsResponse.getArticles());
        mArticleListAdapter.notifyDataSetChanged();
    }


}
