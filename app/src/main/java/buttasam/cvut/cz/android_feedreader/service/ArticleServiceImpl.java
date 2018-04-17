package buttasam.cvut.cz.android_feedreader.service;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buttasam.cvut.cz.android_feedreader.database.ArticleTable;
import buttasam.cvut.cz.android_feedreader.database.ReaderContentProvider;
import buttasam.cvut.cz.android_feedreader.model.Article;


/**
 * @author Samuel Butta
 */

public class ArticleServiceImpl implements ArticleService {

    private ContentResolver contentResolver;

    public ArticleServiceImpl(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void saveArticle(Article article) {
        ContentValues cv = new ContentValues();
        cv.put(ArticleTable.TITLE, article.getTitle());
        cv.put(ArticleTable.AUTHOR, article.getAuthor());
        cv.put(ArticleTable.DATE, article.getDate().toString());
        cv.put(ArticleTable.CONTENT, article.getContent());
        cv.put(ArticleTable.URL, article.getUrl());

        contentResolver.insert(ReaderContentProvider.CONTENT_URI, cv);
    }

    @Override
    public void deleteArticles() {
        contentResolver.delete(ReaderContentProvider.CONTENT_URI, null, new String[]{} );
    }

    @Override
    public List<Article> allArticles() {
        Cursor cursor = contentResolver.query(ReaderContentProvider.CONTENT_URI,
                new String[]{ArticleTable.ID, ArticleTable.TITLE, ArticleTable.AUTHOR, ArticleTable.DATE, ArticleTable.CONTENT, ArticleTable.URL}, null, null, null);

        assert cursor != null;
        int idIndex = cursor.getColumnIndex(ArticleTable.ID);
        int titleIndex = cursor.getColumnIndex(ArticleTable.TITLE);
        int authorIndex = cursor.getColumnIndex(ArticleTable.AUTHOR);
        int dateIndex = cursor.getColumnIndex(ArticleTable.DATE);
        int contentIndex = cursor.getColumnIndex(ArticleTable.CONTENT);
        int urlIndex = cursor.getColumnIndex(ArticleTable.URL);

        List<Article> articles = new ArrayList<>();
        while (cursor.moveToNext()) {
            articles.add(new Article(
                    cursor.getInt(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(authorIndex),
                    new Date(),
                    cursor.getString(contentIndex),
                    cursor.getString(urlIndex)
            ));

        }

        cursor.close();

        return articles;
    }


    @Override
    public Article articleById(int id) {
        Cursor cursor = contentResolver.query(Uri.withAppendedPath(ReaderContentProvider.CONTENT_URI, Integer.toString(id)),
                new String[]{ArticleTable.ID, ArticleTable.TITLE, ArticleTable.AUTHOR, ArticleTable.DATE, ArticleTable.CONTENT, ArticleTable.URL},
                null, null, null);
        cursor.moveToFirst();

        int idIndex = cursor.getColumnIndex(ArticleTable.ID);
        int titleIndex = cursor.getColumnIndex(ArticleTable.TITLE);
        int authorIndex = cursor.getColumnIndex(ArticleTable.AUTHOR);
        int dateIndex = cursor.getColumnIndex(ArticleTable.DATE);
        int contentIndex = cursor.getColumnIndex(ArticleTable.CONTENT);
        int urlIndex = cursor.getColumnIndex(ArticleTable.URL);

        Article article = new Article(
                cursor.getInt(idIndex),
                cursor.getString(titleIndex),
                cursor.getString(authorIndex),
                new Date(),
                cursor.getString(contentIndex),
                cursor.getString(urlIndex)
        );


        cursor.close();
        return article;
    }

}
