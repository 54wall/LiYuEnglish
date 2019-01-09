package pri.weiqiang.liyuenglish.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.mvp.bean.Book;
import pri.weiqiang.liyuenglish.mvp.bean.Lesson;

public class RightMenuAdapter extends RecyclerView.Adapter {

    private final int TYPE_HEAD = 0;
    private final int TYPE_CONTENT = 1;
    private OnItemClickListener onItemClickListener;
    private String TAG = RightMenuAdapter.class.getSimpleName();
    private Context mContext;
    private List<Book> mBookList;
    private int mItemCount;

    public RightMenuAdapter(Context mContext, List<Book> mBookList) {
        this.mContext = mContext;
        this.mBookList = mBookList;
        this.mItemCount = mBookList.size();
        for (Book menu : mBookList) {
            mItemCount += menu.getLessonList().size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int sum = 0;
        for (Book menu : mBookList) {
            if (position == sum) {
                return TYPE_HEAD;
            }
            sum += menu.getLessonList().size() + 1;
        }
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_menu_header, parent, false);
            HeadViewHolder viewHolder = new HeadViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_menu_content, parent, false);
            ContentViewHolder viewHolder = new ContentViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder");
        if (getItemViewType(position) == TYPE_HEAD) {
            HeadViewHolder headHolder = (HeadViewHolder) holder;
            if (headHolder != null) {
                headHolder.mTvBook.setText(getMenuByPosition(position).getName());
                headHolder.mLlRightMenuHead.setContentDescription(position + "");
            }
        } else {
            final ContentViewHolder contentHolder = (ContentViewHolder) holder;
            if (contentHolder != null) {

                final Lesson lesson = getDishByPosition(position);
                int i = lesson.getTitle().indexOf("_", 0);
                String title = lesson.getTitle().substring(0, i) + " 第" + lesson.getTitle().substring(i + 1) + "课";
                contentHolder.mTvLesson.setText(title);
                contentHolder.mLlRightItem.setContentDescription(position + "");
                contentHolder.item = lesson;

            }
            contentHolder.mLlRightItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(contentHolder.item);
                    }

                }
            });
        }
    }

    private Book getMenuByPosition(int position) {
        int sum = 0;
        for (Book menu : mBookList) {
            if (position == sum) {
                return menu;
            }
            sum += menu.getLessonList().size() + 1;
        }
        return null;
    }

    private Lesson getDishByPosition(int position) {
        for (Book menu : mBookList) {
            if (position > 0 && position <= menu.getLessonList().size()) {
                return menu.getLessonList().get(position - 1);
            } else {
                position -= menu.getLessonList().size() + 1;
            }
        }
        return null;
    }

    public Book getMenuOfMenuByPosition(int position) {
        for (Book menu : mBookList) {
            if (position == 0) return menu;
            if (position > 0 && position <= menu.getLessonList().size()) {
                return menu;
            } else {
                position -= menu.getLessonList().size() + 1;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClick(Lesson item);

    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlRightMenuHead;
        private TextView mTvBook;

        HeadViewHolder(View itemView) {
            super(itemView);
            mLlRightMenuHead = itemView.findViewById(R.id.ll_right_menu_head);
            mTvBook = itemView.findViewById(R.id.tv_book);
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        Lesson item;
        private TextView mTvLesson;
        private LinearLayout mLlRightItem;

        ContentViewHolder(View itemView) {
            super(itemView);
            mTvLesson = itemView.findViewById(R.id.tv_lesson);
            mLlRightItem = itemView.findViewById(R.id.ll_right_item);
        }

    }
}
