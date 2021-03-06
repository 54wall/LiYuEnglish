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
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.BeforeDailyEntity;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.DisplaybleItem;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.LatestDailyEntity;
import pri.weiqiang.liyuenglish.mvp.presenter.BasePresenter;
import pri.weiqiang.liyuenglish.mvp.presenter.ZhihuFragmentPresenterImpl;
import pri.weiqiang.liyuenglish.mvp.view.BaseView;
import pri.weiqiang.liyuenglish.ui.adapter.zhihu.HYArticleListAdapter;
import pri.weiqiang.liyuenglish.ui.adapter.zhihu.HomeHeaderItem;
import pri.weiqiang.liyuenglish.ui.adapter.zhihu.HomeSectionItem;

/**
 * Created by weiqiang on 2018/4/11.
 */

public class ZhihuFragment extends BaseFragment implements BaseView.ZhihuFragmentView {

    private static final String TAG = ZhihuFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    private BasePresenter.ZhihuFragmentPresenter presenter;
    private HYArticleListAdapter mArticleListAdapter;
    private List<DisplaybleItem> mArticleList;

    // 用于获取以前的文章列表
    private String mdate;


    public static ZhihuFragment newInstance() {
        ZhihuFragment fragment = new ZhihuFragment();
        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        presenter = new ZhihuFragmentPresenterImpl(this);
        mArticleList = new ArrayList<>();
        mArticleListAdapter = new HYArticleListAdapter(getContext(), mArticleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mArticleListAdapter);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh()");
                presenter.initZhihuFragment();
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
                        Log.e(TAG, "getBeforeDaily:" + "lastChildBottomY:" + lastChildBottomY + ",recyclerBottomY:" + recyclerBottomY + ",lastPosition:" + lastPosition);
                        presenter.getBeforeDaily(mdate);
                    }
                }
            }
        });
    }

    @Override
    protected void doAction() {

        presenter.initZhihuFragment();

    }


    @Override
    public <T> void refreshHomeList(T t) {
        mArticleList.clear();
        LatestDailyEntity latestDailyEntity = (LatestDailyEntity) t;
        mdate = latestDailyEntity.getDate();
        Log.e(TAG, "mdate:" + mdate);
        mArticleList.add(new HomeHeaderItem(latestDailyEntity.getTop_stories()));
        mArticleList.add(new HomeSectionItem(mdate));
        mArticleList.addAll(latestDailyEntity.getStories());
        mArticleListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void loadBeforeDaily(BeforeDailyEntity beforeDailyEntity) {
        mdate = beforeDailyEntity.getDate();
        mArticleList.add(new HomeSectionItem(mdate));
        mArticleList.addAll(beforeDailyEntity.getStories());
        mArticleListAdapter.notifyDataSetChanged();
    }
}
