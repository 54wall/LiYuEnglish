package pri.weiqiang.liyuenglish;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.blankj.utilcode.utils.NetworkUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.manager.SharedPreferenceManager;

public class MyApplication extends Application {

    public static int FROM_LAN = 0;
    public static int TO_LAN = 0;
    private static boolean ISWIFI = false;
    private static MyApplication instance = null;

    public synchronized static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        CrashReport.initCrashReport(getApplicationContext(), "0a341dafed", false);
        //使用LeakCanary检测内存泄露
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        instance = this;

        setDayNightMode(SharedPreferenceManager.getInstance().getString(Constants.MODE_THEME, Constants.MODE_DAY));

        ISWIFI = NetworkUtils.isWifiConnected(this);

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    private void setDayNightMode(String mode) {

        switch (mode) {
            case Constants.MODE_AUTO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
            case Constants.MODE_NIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Constants.MODE_DAY:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

        }

    }
}
