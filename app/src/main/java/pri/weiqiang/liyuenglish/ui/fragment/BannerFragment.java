package pri.weiqiang.liyuenglish.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.mvp.bean.BannerItem;

public class BannerFragment extends BaseFragment {

    @BindView(R.id.iv_banner)
    ImageView mImageView;

    private int banner;

    public static BannerFragment newInstance(BannerItem item) {

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.IMG_BANNER, item.getBanner());
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(arguments);

        return fragment;

    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        banner = getArguments().getInt(Constants.IMG_BANNER);

    }

    @Override
    protected void doAction() {
        Glide.with(getContext())/*.asBitmap()*/.load(banner).into(mImageView);
    }
}
