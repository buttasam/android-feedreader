package buttasam.cvut.cz.android_feedreader.service;

import java.util.ArrayList;
import java.util.List;

import buttasam.cvut.cz.android_feedreader.model.Article;

/**
 *
 * Mock service class
 *
 * @author Samuel Butta
 */
public class ArticleMockService implements ArticleService {

    @Override
    public List<Article> downloadNewArticles() {
        List<Article> articles = new ArrayList<>();

        articles.add(new Article("Dummmy title 1", "Dummy content 1"));
        articles.add(new Article("Dummmy title 2", "Dummy content 2"));
        articles.add(new Article("Dummmy title 3", "Dummy content 3"));

        return articles;
    }

}
