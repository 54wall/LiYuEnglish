package pri.weiqiang.liyuenglish.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

/**
 * 系统播报类,部分手机不支持中文播报
 */
@SuppressLint("NewApi")
public class SystemTTS extends UtteranceProgressListener implements TTS, OnUtteranceCompletedListener {
    private static SystemTTS singleton;
    TTSICallBack callBack = null;
    private String TAG = SystemTTS.class.getSimpleName();
    private Context mContext;
    private TextToSpeech textToSpeech; // 系统语音播报类
    private boolean isSuccess = true;

    private SystemTTS(Context context) {
        this.mContext = context.getApplicationContext();
        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                //系统语音初始化成功
                if (i == TextToSpeech.SUCCESS) {
                    Log.e(TAG, "onInit");
                    int result = textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    textToSpeech.setSpeechRate(1.0f);
                    textToSpeech.setOnUtteranceProgressListener(SystemTTS.this);
                    textToSpeech.setOnUtteranceCompletedListener(SystemTTS.this);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "系统不支持中文播报");
                        //系统不支持中文播报
                        isSuccess = false;
                    }
                }

            }
        });
    }

    public static SystemTTS getInstance(Context context) {
        if (singleton == null) {
            synchronized (SystemTTS.class) {
                if (singleton == null) {
                    singleton = new SystemTTS(context);
                }
            }
        }
        return singleton;
    }

    public void destroy() {
        stopSpeak();
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
        singleton = null;
    }

    public void init() {
    }

    public void playText(String playText) {
        if (!isSuccess) {
            Log.e(TAG, "playText failed");
            return;
        }
        if (textToSpeech != null) {
            textToSpeech.speak(playText,
                    TextToSpeech.QUEUE_ADD, null, null);
            Log.e(TAG, "playText success");
        }
    }

    public void stopSpeak() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    @Override
    public boolean isPlaying() {
        return textToSpeech.isSpeaking();
    }

    @Override
    public void setCallback(TTSICallBack callback) {
        callBack = callback;
    }


    //播报完成回调
    @Override
    public void onUtteranceCompleted(String utteranceId) {
    }

    @Override
    public void onStart(String utteranceId) {

    }

    @Override
    public void onDone(String utteranceId) {
    }

    @Override
    public void onError(String utteranceId) {

    }
}