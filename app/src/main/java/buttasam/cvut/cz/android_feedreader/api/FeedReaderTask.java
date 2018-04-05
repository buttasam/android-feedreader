package buttasam.cvut.cz.android_feedreader.api;

import android.os.AsyncTask;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;

/**
 * @author Samuel Butta
 */

public class FeedReaderTask extends AsyncTask<String, Void, Void> {


    @Override
    protected Void doInBackground(String... urls) {
        for (String url : urls) {
            SyndFeed feed = downloadFeed(url);

            for(SyndEntry entry: feed.getEntries()) {
                System.out.println(entry.getTitle());
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