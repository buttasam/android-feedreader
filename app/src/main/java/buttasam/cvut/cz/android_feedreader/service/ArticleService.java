package buttasam.cvut.cz.android_feedreader.service;

import java.util.List;

import buttasam.cvut.cz.android_feedreader.model.Article;

/**
 * @author Samuel Butta
 */
public interface ArticleService {

    List<Article> downloadNewArticles();

}
