package pri.weiqiang.liyuenglish.ui.adapter.zhihu;

import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.loader.GlideImageLoader;
import pri.weiqiang.liyuenglish.mvp.bean.zhihu.DisplaybleItem;

/**
 * Created by Administrator on 2017/1/9.
 */

class ThemeHeaderItemDelegate implements ItemViewDelegate<DisplaybleItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.theme_header;
    }

    @Override
    public boolean isForViewType(DisplaybleItem item, int position) {
        return item instanceof ThemeHeaderItem;
    }

    @Override
    public void convert(ViewHolder holder, DisplaybleItem displaybleItem, int position) {
        ThemeHeaderItem item = (ThemeHeaderItem) displaybleItem;

        holder.setText(R.id.theme_header_tv, item.getDescription());

        ImageView iv = holder.getView(R.id.theme_header_iv);
        //Glide.with(holder.getConvertView().getContext()).load(item.getImage()).into(iv);
        GlideImageLoader.getInstance().displayImage(holder.getConvertView().getContext(), item.getImage(), iv);
    }
}
