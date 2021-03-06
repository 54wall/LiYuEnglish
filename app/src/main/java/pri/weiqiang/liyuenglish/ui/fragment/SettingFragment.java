package pri.weiqiang.liyuenglish.ui.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import pri.weiqiang.liyuenglish.R;
import pri.weiqiang.liyuenglish.config.Constants;
import pri.weiqiang.liyuenglish.manager.SharedPreferenceManager;
import pri.weiqiang.liyuenglish.rxbus.RxBus;
import pri.weiqiang.liyuenglish.rxbus.event.EventContainer;
import pri.weiqiang.liyuenglish.rxbus.event.SettingEvent;


public class SettingFragment extends PreferenceFragment {

    private final String TAG = getClass().getSimpleName();

    private ListPreference mTTSListPreference;
    private ListPreference mThemesListPreference;

    private String mode_theme;
    private String tts_type;
    private boolean allow_connect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initVariable();
        initPreference();

        Log.i(TAG, "onCreate: OK");

    }

    private void initView() {
        addPreferencesFromResource(R.xml.preference_setting);
    }


    private void initVariable() {

        tts_type = SharedPreferenceManager.getInstance().getString(Constants.TTS_TYPE, Constants.TTS_MALE_01);
        mode_theme = SharedPreferenceManager.getInstance().getString(Constants.MODE_THEME, Constants.MODE_DAY);
        allow_connect = SharedPreferenceManager.getInstance().getBoolean(Constants.ALLOW_CONNECT_WITHOUT_WIFI, false);

    }


    private void initPreference() {

        mTTSListPreference = (ListPreference) getPreferenceManager().findPreference("setting_tts");
        mTTSListPreference.setEntries(R.array.tts_entries);
        mTTSListPreference.setEntryValues(R.array.tts_values);
        mTTSListPreference.setValue(tts_type);
        mTTSListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                return true;
            }
        });

        mThemesListPreference = (ListPreference) getPreferenceManager().findPreference("setting_theme");
        mThemesListPreference.setEntries(R.array.themes_entries);
        mThemesListPreference.setEntryValues(R.array.themes_values);
        mThemesListPreference.setValue(mode_theme);
        mThemesListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                SharedPreferenceManager.getInstance().putString(Constants.MODE_THEME, (String) newValue);
                RxBus.getDefault().post(new EventContainer(EventContainer.TYPE_SETTING, new SettingEvent(R.string.reboot_to_take_effect)));

                return true;
            }
        });
    }
}
