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


    private Map<Integer, Feed> dummyData = new HashMap<>();

    public FeedMockService() {
        dummyData.put(1, new Feed(1, "Technet", "http://servis.idnes.cz/rss.aspx?c=technet"));
        dummyData.put(2, new Feed(2, "Zprávy iDNES.cz", "http://servis.idnes.cz/rss.aspx?c=zpravodaj"));
        dummyData.put(3, new Feed(3, "Sport iDNES.cz", "http://servis.idnes.cz/rss.aspx?c=technet"));
    }

    @Override
    public void saveFeed(Feed feed) {
        dummyData.put(dummyData.size() + 1, feed);
    }

    @Override
    public void deleteFeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Feed> allFeeds() {
        return new ArrayList<Feed>(dummyData.values());
    }
}
