package buttasam.cvut.cz.android_feedreader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.activity.ArticleDetailActivity;
import buttasam.cvut.cz.android_feedreader.model.Article;
import buttasam.cvut.cz.android_feedreader.service.ArticleMockService;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;


public class ArticlesFragment extends Fragment {

    private ArticleService articleService = new ArticleMockService();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_articles, container, false);

        List<View> articleViews = generateViews(inflater, container, articleService.downloadNewArticles());

        for (View articleView : articleViews) {
            layout.addView(articleView);
        }

        return layout;
    }

    private List<View> generateViews(LayoutInflater inflater, ViewGroup parent, List<Article> articles) {
        List<View> articleViews = new ArrayList<>();
        for (Article article : articles) {
            articleViews.add(generateView(inflater, parent, article));
        }
        return articleViews;
    }

    private View generateView(LayoutInflater inflater, ViewGroup parent, Article article) {
        View view = inflater.inflate(R.layout.article_preview, parent);
        TextView title = view.findViewById(R.id.preview_title);
        TextView content = view.findViewById(R.id.preview_content);

        title.setText(article.getTitle());
        content.setText(article.getContent());

        view.setOnClickListener(new ArticleOnClickListener(article.getId()));

        return view;
    }

    private class ArticleOnClickListener implements View.OnClickListener {

        private long articleId;

        ArticleOnClickListener(long articleId) {
            this.articleId = articleId;
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailActivity.ARTICLE_ID, articleId);
            startActivity(intent);

        }
    }
}
