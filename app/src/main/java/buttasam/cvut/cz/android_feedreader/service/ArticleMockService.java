package buttasam.cvut.cz.android_feedreader.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        dummyData.put(1, new Article(1, "První článek", "Pepíček", randomDate(), "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "www.seznam.cz"));
        dummyData.put(2, new Article(2, "Druhý článek", "Jarmila", randomDate(), "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.", "www.google.cz"));
        dummyData.put(3, new Article(3, "Třetí článek", "Cecil", randomDate(), "Nunc eu pharetra urna. Sed lacinia ante a urna bibendum porta. Quisque non tempus est. Cras sagittis eros ut malesuada convallis. Ut leo arcu, auctor sed pulvinar ultrices, porta eu nibh. Maecenas a nisi mauris. Nam tempus a risus sed ultrices. Etiam pharetra pellentesque quam, at ultricies eros pulvinar a. Pellentesque ut mollis sem, condimentum convallis urna. Nulla facilisi. Aenean sollicitudin lectus a mauris facilisis, in eleifend est convallis.", "www.idnes.cz"));

    }

    @Override
    public List<Article> downloadNewArticles() {
        return new ArrayList<Article>(dummyData.values());
    }

    @Override
    public Article articleById(int id) {
        return dummyData.get(id);
    }

    private Date randomDate() {
        Random random = new Random();
        long diff = random.nextInt();
        return new Date(new Date().getTime() - diff);
    }
}
