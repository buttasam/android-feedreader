package buttasam.cvut.cz.android_feedreader.api;

import android.os.AsyncTask;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

import buttasam.cvut.cz.android_feedreader.model.Article;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;

/**
 * @author Samuel Butta
 */

public class FeedReaderTask extends AsyncTask<String, Void, Void> {

    private final ArticleService articleService;

    public FeedReaderTask(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Override
    protected Void doInBackground(String... urls) {
        for (String url : urls) {
            SyndFeed feed = downloadFeed(url);
            for(SyndEntry entry: feed.getEntries()) {
                Article article = new Article();
                article.setTitle(entry.getTitle());
                article.setAuthor(entry.getAuthor());
                article.setDate(entry.getPublishedDate());
                String content = "";
                if(entry.getContents().size() > 0) {
                    content = entry.getContents().get(0).getValue();
                } else if (entry.getDescription() != null) {
                    content = entry.getDescription().getValue();
                }
                article.setContent(content);
                article.setUrl(entry.getUri());

                articleService.saveArticle(article);
            }
        }
        return null;
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