package pri.weiqiang.liyuenglish.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.LayoutAnimationController;

import java.util.List;

import butterknife.BindView;
import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.mvp.bean.Word;
import pri.weiqiang.liyuenglish.mvp.presenter.BasePresenter;
import pri.weiqiang.liyuenglish.mvp.presenter.WordsFragmentPresenterImpl;
import pri.weiqiang.liyuenglish.mvp.view.BaseView;
import pri.weiqiang.liyuenglish.ui.adapter.WordsRecyclerAdapter;
import pri.weiqiang.liyuenglish.utils.LayoutAnimationHelper;

/**
 * Created by weiqiang on 2018/3/16.
 */

public class WordsFragment extends BaseFragment implements BaseView.WordsFragmentView {

    private static final String TAG = WordsFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefresh;
    private BasePresenter.WordsFragmentPresenter presenter;
    private WordsRecyclerAdapter adapter;
    private String lesson;
    private Boolean isExpandable;

    public static WordsFragment newInstance(String lesson, Boolean isExpandable) {

        Bundle argument = new Bundle();
        argument.putString(Constants.FLAG_LESSON, lesson);
        argument.putBoolean(Constants.FLAG_IS_EXPANDABLE, isExpandable);
        WordsFragment fragment = new WordsFragment();
        fragment.setArguments(argument);

        return fragment;

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_words;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        lesson = getArguments().getString(Constants.FLAG_LESSON);
        isExpandable = getArguments().getBoolean(Constants.FLAG_IS_EXPANDABLE);
        Log.e(TAG, "lesson:" + lesson);
        presenter = new WordsFragmentPresenterImpl(this, lesson);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh()");
                presenter.randomList();
                LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromLeft(), LayoutAnimationController.ORDER_RANDOM);
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void doAction() {
        presenter.initWordsFragment();
    }

    @Override
    public void setData(List<Word> data) {
        adapter = new WordsRecyclerAdapter(getContext(), data, isExpandable);
        mRecyclerView.setAdapter(adapter);
        LayoutAnimationHelper.getInstance().playLayoutAnimation(mRecyclerView, LayoutAnimationHelper.getInstance().getAnimationSetFromBottom(), LayoutAnimationController.ORDER_NORMAL);
        adapter.setOnItemClickListener(new WordsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Word bean) {

            }
        });

    }

    @Override
    public void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
