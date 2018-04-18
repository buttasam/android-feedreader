package buttasam.cvut.cz.android_feedreader.api;

import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.View;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

import buttasam.cvut.cz.android_feedreader.model.Article;
import buttasam.cvut.cz.android_feedreader.model.Feed;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;
import buttasam.cvut.cz.android_feedreader.service.FeedMockService;
import buttasam.cvut.cz.android_feedreader.service.FeedService;

/**
 * @author Samuel Butta
 */
public class FeedReaderTask extends AsyncTask<String, Void, Void> {

    private final ArticleService articleService;
    private final FeedService feedService = FeedMockService.getInstance();
    private MenuItem refreshMenuItem;
    private View mProgressActionView;


    public FeedReaderTask(ArticleService articleService, MenuItem refreshMenuItem, View mProgressActionView) {
        this.articleService = articleService;
        this.refreshMenuItem = refreshMenuItem;
        this.mProgressActionView = mProgressActionView;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        refreshMenuItem.setActionView(mProgressActionView);
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        refreshMenuItem.setActionView(null);
    }

    @Override
    protected Void doInBackground(String... urls) {
        for (Feed resource : feedService.allFeeds()) {
            SyndFeed feed = downloadFeed(resource.getUrl());
            assert feed != null;
            for (SyndEntry entry : feed.getEntries()) {

                Article article = new Article();
                article.setTitle(entry.getTitle());
                article.setAuthor(entry.getAuthor());
                article.setDate(entry.getPublishedDate());
                article.setContent(cleanContent(entry));
                article.setUrl(entry.getUri());

                articleService.saveArticle(article);
            }
        }
        return null;
    }

    private String cleanContent(SyndEntry entry) {
        String content = "";
        if (entry.getContents().size() > 0) {
            content = entry.getContents().get(0).getValue();
        } else if (entry.getDescription() != null) {
            content = entry.getDescription().getValue();
        }

        return content;
    }

    private SyndFeed downloadFeed(String url) {
        SyndFeedInput input = new SyndFeedInput();
        try {
            URL feedUrl = new URL(url);
            return input.build(new XmlReader(feedUrl));
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}