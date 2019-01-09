package pri.weiqiang.liyuenglish.mvp.presenter;

import android.util.Log;

import io.reactivex.functions.Consumer;
import pri.weiqiang.liyuenglish.MyApplication;
import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.manager.ClipboardManager;
import pri.weiqiang.liyuenglish.mvp.bean.BaiduTranslateBean;
import pri.weiqiang.liyuenglish.mvp.model.BaseModel;
import pri.weiqiang.liyuenglish.mvp.model.TranslateFragmentModelImpl;
import pri.weiqiang.liyuenglish.mvp.view.BaseView;
import pri.weiqiang.liyuenglish.network.baidu.BaiduTranslateApi;
import pri.weiqiang.liyuenglish.utils.StringUtils;


public class TranslateFragmentPresenterImpl extends BasePresenter<BaseView.TranslateFragmentView> implements BasePresenter.TranslateFragmentPresenter {

    private BaseModel.TranslateFragmentModel model;
    private String TAG = TranslateFragmentPresenterImpl.class.getSimpleName();

    public TranslateFragmentPresenterImpl(BaseView.TranslateFragmentView view) {
        super(view);
        model = new TranslateFragmentModelImpl();
    }

    @Override
    public void initTranslateFragment() {

        view.setFromSpinner(model.getFromList());
        view.setToSpinner(model.getToList());

    }

    @Override
    public void checkFromLanguate(int from) {
        MyApplication.FROM_LAN = from;
    }

    @Override
    public void checkToLanguage(int to) {
        MyApplication.TO_LAN = to;
    }

    @Override
    public void checkImageViewClick(int id) {

        switch (id) {

            case R.id.iv_src_copy:

                String srcText = view.getSrcText();
                if (!StringUtils.isNullOrEmpty(srcText)) {
                    ClipboardManager.getInstance().setText("label", view.getSrcText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_src_paste:
                view.setSrcEditText(ClipboardManager.getInstance().getText());
                break;
            case R.id.iv_src_clear:
                view.setSrcEditText("");
                break;
            case R.id.iv_dst_copy:

                String dstText = view.getDstText();
                if (!StringUtils.isNullOrEmpty(dstText)) {
                    ClipboardManager.getInstance().setText("label", view.getDstText());
                    view.showMsg(R.string.copy_successfully);
                }
                break;
            case R.id.iv_dst_clear:
                view.setDstTextView("");
                break;
            default:
                break;
        }

    }

    @Override
    public void doTranslate() {

        String srcText = view.getSrcText();

        if (!StringUtils.isNullOrEmpty(srcText)) {

            String from;

            switch (MyApplication.FROM_LAN) {

                case 0:
                    from = BaiduTranslateApi.AUTO;
                    break;
                case 1:
                    from = BaiduTranslateApi.ZH;
                    break;
                case 2:
                    from = BaiduTranslateApi.EN;
                    break;
                case 3:
                    from = BaiduTranslateApi.JP;
                    break;
                default:
                    from = BaiduTranslateApi.AUTO;
                    break;

            }

            String to;

            switch (MyApplication.TO_LAN) {

                case 0:
                    to = BaiduTranslateApi.ZH;
                    break;
                case 1:
                    to = BaiduTranslateApi.EN;
                    break;
                case 2:
                    to = BaiduTranslateApi.JP;
                    break;
                default:
                    to = BaiduTranslateApi.ZH;
                    break;

            }

            model.translate(view.getSrcText(), from, to, new Consumer<BaiduTranslateBean>() {

                @Override
                public void accept(BaiduTranslateBean baiduTranslateBean) throws Exception {
                    if (baiduTranslateBean.getError_code() == null) {
                        view.setDstTextView(baiduTranslateBean.getTrans_result()[0].getDst());
                    } else {
                        view.showMsg(baiduTranslateBean.getError_msg());
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e(TAG, "model.translate-throwable:" + throwable.toString());
                }
            });
        }


    }
}
