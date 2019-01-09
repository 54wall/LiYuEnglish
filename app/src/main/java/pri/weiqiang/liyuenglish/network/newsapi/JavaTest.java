package pri.weiqiang.liyuenglish.network.newsapi;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JavaTest {
    private static String TAG = JavaTest.class.getSimpleName();

    public static void main(String[] args) {
        System.out.println(TAG + " main start");
        ignoreSSLCheck();
    }

    private static void ignoreSSLCheck() {
        System.out.println(TAG + " main ignoreSSLCheck");
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        //使用全称报错 com.squareup.okhttp3  java.lang.ClassNotFoundException: com.squareup.okhttp3.OkHttpClient
        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            System.out.println(TAG + " main ignoreSSLCheck " + workerClass.getName());
            System.out.println(TAG + " main ignoreSSLCheck " + workerClass.getDeclaredMethods());
            Method[] methods = workerClass.getDeclaredMethods();
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
//            hostnameVerifier.set(sClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
//            sslSocketFactory.set(sClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
