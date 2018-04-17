package buttasam.cvut.cz.android_feedreader.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author Samuel Butta
 */

public class ArticleTable {

    public static final String TABLE_ARTICLE = "article";
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String DATE = "date";
    public static final String CONTENT = "content";
    public static final String URL = "url";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_ARTICLE
            + "("
            + ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + AUTHOR + " text not null, "
            + DATE + " text not null, "
            + CONTENT + " text not null, "
            + URL + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAndCreateTable(db);
    }

    public static void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAndCreateTable(db);
    }

    public static void dropAndCreateTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);
        onCreate(db);
    }

    public static void clearTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);
    }

}
