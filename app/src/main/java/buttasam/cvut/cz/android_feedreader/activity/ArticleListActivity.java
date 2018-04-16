package buttasam.cvut.cz.android_feedreader.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.database.ArticleTable;
import buttasam.cvut.cz.android_feedreader.database.ReaderContentProvider;

import static buttasam.cvut.cz.android_feedreader.database.ArticleTable.CONTENT;
import static buttasam.cvut.cz.android_feedreader.database.ArticleTable.ID;
import static buttasam.cvut.cz.android_feedreader.database.ArticleTable.TITLE;

public class ArticleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        fillDatabase();
        showArticles();
    }


    private void showArticles() {
        Cursor cursor = getContentResolver().query(ReaderContentProvider.CONTENT_URI, new String[] {ID, TITLE, CONTENT}, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(ID);
        int titleColumnIndex = cursor.getColumnIndex(TITLE);
        int textColumnIndex = cursor.getColumnIndex(CONTENT);
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(idColumnIndex) + " " + cursor.getString(titleColumnIndex));
        }

        cursor.close();
    }


    private void fillDatabase() {
        ContentValues cv = new ContentValues();
        cv.put(ArticleTable.TITLE, "Korespondencni seminar");
        cv.put(ArticleTable.AUTHOR, "Samuel Butta");
        cv.put(ArticleTable.DATE, "12-21-2015");
        cv.put(ArticleTable.CONTENT, "Muze se zucastnit opravdu kazdy student...");
        cv.put(ArticleTable.URL, "www.seznam.cz");

        insertContentValue(cv);

        cv.put(ArticleTable.TITLE, "Clanek 2");
        cv.put(ArticleTable.AUTHOR, "Samuel Butta");
        cv.put(ArticleTable.DATE, "12-21-2015");
        cv.put(ArticleTable.CONTENT, "Obsah claku 2");
        cv.put(ArticleTable.URL, "www.seznam.cz");

        insertContentValue(cv);
    }

    private void insertContentValue(ContentValues cv){
        getContentResolver().insert(ReaderContentProvider.CONTENT_URI, cv);
    }

}
