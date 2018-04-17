package buttasam.cvut.cz.android_feedreader.service;

import java.util.List;

import buttasam.cvut.cz.android_feedreader.model.Feed;

/**
 * @author Samuel Butta
 */
public interface FeedService {

    void saveFeed(Feed feed);

    void deleteFeed();

    List<Feed> allFeeds();

}
