package pri.weiqiang.liyuenglish.network.youdao;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import pri.weiqiang.liyuenglish.utils.UriUtil;

//https://blog.csdn.net/tz_yhj/article/details/54577680

public class YoudaoAudioService extends Service {
    private String TAG = YoudaoAudioService.class.getSimpleName();
    private MediaPlayer mp;
    private String query;

    @Override
    public void onCreate() {
        Log.e(TAG, "初始化音乐资源");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (query != null && query.equals(intent.getStringExtra("query")) && mp != null) {
            mp.start();
        } else {
            query = intent.getStringExtra("query");
            Log.e(TAG, "query:" + query);
            new Thread() {
                public void run() {

                    if (UriUtil.checkURL("http://dict.youdao.com/dictvoice?audio=" + query)) {
                        try {
                            Uri location = Uri.parse("http://dict.youdao.com/dictvoice?audio=" + query);
                            mp = MediaPlayer.create(YoudaoAudioService.this, location);
                            mp.start();
                            // 音乐播放完毕的事件处理
                            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    Log.e(TAG, "音乐播放完毕的事件处理");
                                    // 不循环播放
                                    try {
                                        // mp.start();
                                        System.out.println("stopped");
                                    } catch (IllegalStateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            // 播放音乐时发生错误的事件处理
                            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                                public boolean onError(MediaPlayer mp, int what, int extra) {
                                    Log.e(TAG, "播放音乐时发生错误的事件处理");
                                    // 释放资源
                                    try {
                                        mp.release();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return false;
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Exception:" + e.toString() + "未联网或者服务器无法访问");
                        }
                    }

                }
            }.start();
        }
        // super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        // 服务停止时停止播放音乐并释放资源
        mp.stop();
        mp.release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}