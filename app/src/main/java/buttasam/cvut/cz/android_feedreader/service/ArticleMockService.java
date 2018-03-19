package buttasam.cvut.cz.android_feedreader.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import buttasam.cvut.cz.android_feedreader.model.Article;

/**
 *
 * Mock service class
 *
 * @author Samuel Butta
 */
public class ArticleMockService implements ArticleService {


    private Map<Integer, Article> dummyData = new HashMap<>();

    public ArticleMockService() {
        dummyData.put(1, new Article("Dummmy title 1", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));
        dummyData.put(2, new Article("Dummmy title 2", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));
        dummyData.put(3, new Article("Dummmy title 3", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));
        dummyData.put(4, new Article("Dummmy title 4", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));
        dummyData.put(5, new Article("Dummmy title 5", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));
        dummyData.put(6, new Article("Dummmy title 6", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean placerat. Mauris elementum mauris vitae tortor. Quisque tincidunt scelerisque libero. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Aliquam erat volutpat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce suscipit libero eget elit. Etiam egestas wisi a erat. Pellentesque pretium lectus id turpis. Quisque tincidunt scelerisque libero. Nullam sit amet magna in magna gravida vehicula. Suspendisse nisl. Integer tempor. Etiam dictum tincidunt diam." ));

    }

    @Override
    public List<Article> downloadNewArticles() {
        return new ArrayList<Article>(dummyData.values());
    }

    @Override
    public Article articleById(int id) {
        return dummyData.get(id);
    }

}
