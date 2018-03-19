package buttasam.cvut.cz.android_feedreader.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.model.Article;
import buttasam.cvut.cz.android_feedreader.service.ArticleMockService;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;



public class ArticleDetailFragment extends Fragment {


    private ArticleService articleService = new ArticleMockService();


    public ArticleDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Article article = articleService.articleById(1);

        LinearLayout layout = getActivity().findViewById(R.id.layout_fragment_article_detail);
        View articleView = getLayoutInflater().inflate(R.layout.article, null);

        TextView title = articleView.findViewById(R.id.article_title);
        title.setText(article.getTitle());

        TextView content = articleView.findViewById(R.id.article_content);
        content.setText(article.getContent());

        layout.addView(articleView);

    }
}
