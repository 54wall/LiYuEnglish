package pri.weiqiang.liyuenglish.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import pri.weiqiang.liyuenglish.mvp.bean.BannerItem;
import pri.weiqiang.liyuenglish.ui.fragment.BannerFragment;

public class BannerPagerAdapter extends BasePagerAdapter<BannerItem> {

    public BannerPagerAdapter(FragmentManager fm, List<BannerItem> list) {
        super(fm, list);
    }

    @Override
    protected Fragment getFragment(BannerItem bannerItem) {
        return BannerFragment.newInstance(bannerItem);
    }

    @Override
    protected CharSequence getTitle(BannerItem bannerItem) {
        return bannerItem.getTitle();
    }
}
