package buttasam.cvut.cz.android_feedreader.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import buttasam.cvut.cz.android_feedreader.model.Feed;

/**
 * Mock service class
 *
 * @author Samuel Butta
 */
public class FeedMockService implements FeedService {


    private List<Feed> dummyData = new ArrayList<>();

    public FeedMockService() {
        dummyData.add(new Feed(1, "Technet", "http://servis.idnes.cz/rss.aspx?c=technet"));
        dummyData.add(new Feed(2, "Zprávy iDNES.cz", "http://servis.idnes.cz/rss.aspx?c=zpravodaj"));
        dummyData.add(new Feed(3, "Sport iDNES.cz", "http://servis.idnes.cz/rss.aspx?c=technet"));
    }

    @Override
    public void saveFeed(Feed feed) {
        dummyData.add(feed);
    }

    @Override
    public void deleteFeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Feed> allFeeds() {
        return dummyData;
    }
}
