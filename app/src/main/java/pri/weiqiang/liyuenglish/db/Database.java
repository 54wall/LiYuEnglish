package pri.weiqiang.liyuenglish.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class Database extends SQLiteAssetHelper {

    public static final String DB_NAME = "en.db";
    public static final String DB_TABLE_WORDS = "words";
    public static final String DB_TABLE_LESSONS = "lessons";
    public static final String DB_TABLE_FAV = "fav";
    private static final int DB_VERSON = 1;
    private static Database mInstance = null;

    private Database(Context context) {

        super(context, DB_NAME, null, DB_VERSON);

    }

    /*使用单例模式避免 以下错误A SQLiteConnection object for database '/data/data/.../databases/....db' was leaked!
    Please fix your application to end transactions in progress properly and to close the database when it is no longer needed*/
    public synchronized static Database getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Database(context);
        }
        return mInstance;
    }

    ;

}
