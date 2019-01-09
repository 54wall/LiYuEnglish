package pri.weiqiang.liyuenglish.config;

public class Constants {

    public static final int FR_WORDS = 0;
    public static final int FR_FAV_WORDS = 1;
    public static final int FR_FAV_LESSON = 2;
    public static final int FR_LESSONS = 3;
    public static final int FR_TRANSLATE = 4;
    public static final int FR_ZHIHU = 5;
    public static final int FR_NEWS = 6;

    //Used by intents
    public static final String INTENT_URL = "url";
    //Used to save the instance of the title of Toolbar.
    public static final String TITLE_WEBVIEW_KEY = "save_text_webView";

    public static final String NEWS_COUNTRY = "us";
    public static final String NEWS_CATEGORY_GENERAL = "general";
    public static final String NEWS_CATEGORY_TECHNOLOGY = "technology";
    public static final String NEWS_CATEGORY_SCIENCE = "science";
    public static final String NEWS_CATEGORY_BUSINESS = "business";
    public static final String NEWS_CATEGORY_ENTERTAINMENT = "entertainment";

    public static final String FLAG_ZHIHU_ARTICLE_ID = "article_id";
    public static final String FLAG_ZHIHU_ARTICLE_TITLE = "article_title";
    public static final String FLAG_LESSON = "lesson";
    public static final String FLAG_IS_EXPANDABLE = "is_expandable";
    public static final String PREF_NAME = "pref_jpstart";
    public static final String CURRENT_LESSON_ID = "current_lesson_id";
    public static final int DEFAULT_LESSON_ID = 82;
    public static final String CURRENT_LESSON = "current_lesson";
    public static final String DEFAULT_LESSON = "GRE红宝书(上)_1";
    public static final String FLAG_TIPS_WORD = "flag_tips_word";
    public static final String FLAG_TIPS_TRANSLATE = "flag_tips_translate";
    public static final String TTS_TYPE = "tts_type";
    public static final String TTS_MALE_01 = "male01";
    public static final String MODE_THEME = "mode_theme";
    public static final String MODE_AUTO = "auto";
    public static final String MODE_DAY = "day";
    public static final String MODE_NIGHT = "night";
    public static final String URL_AUTHOR = "https://github.com/54wall";
    public static final String IMG_BANNER = "img_banner";
    public static final String ALLOW_CONNECT_WITHOUT_WIFI = "allow_connect_without_wifi";

    public static final String YOUDAO_TRANSLATE_URL = "http://fanyi.youdao.com";
    public static final String YOUDAO_TRANSLATE_APIKEY = "不再上传秘钥";
    public static final String YOUDAO_TRANSLATE_KEYFROM = "不再上传秘钥";
    public static final String BAIDU_TRANSLATE_URL = "http://api.fanyi.baidu.com";
    public static final String BAIDU_TRANSLATE_APPID = "不再上传秘钥";
    public static final String BAIDU_TRANSLATE_SECRETKEY = "不要上传秘钥";
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
    public static final String API_NEWS_KEY = "ef7c2f7191ec474cbdcd564382744e38";
    public static final String API_NEWS_PAGE = "30";

    private Constants() {
        throw new RuntimeException("异常");
    }


}
