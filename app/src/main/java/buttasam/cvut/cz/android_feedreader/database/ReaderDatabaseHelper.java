package buttasam.cvut.cz.android_feedreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Samuel Butta
 */

public class ReaderDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "feedreader.db";
    public static final int DATABASE_VERSION = 1;

    public ReaderDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArticleTable.onCreate(db);
        System.out.println("DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ArticleTable.onUpgrade(db, oldVersion, newVersion);
    }
}
