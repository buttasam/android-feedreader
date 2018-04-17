package buttasam.cvut.cz.android_feedreader.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.model.Article;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;
import buttasam.cvut.cz.android_feedreader.service.ArticleServiceImpl;


public class ArticleDetailFragment extends Fragment {

    public static final String ARTICLE_ID = "ARTICLE_ID";

    private ArticleService articleService;
    public Article article;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.articleService = new ArticleServiceImpl(getActivity().getContentResolver());

        long articleId = getArguments().getLong(ARTICLE_ID);
        article = articleService.articleById((int) articleId);

        setHasOptionsMenu(article != null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        TextView title = view.findViewById(R.id.titleView);
        TextView author = view.findViewById(R.id.authorView);
        TextView content = view.findViewById(R.id.contentView);

        CharSequence date = DateUtils.getRelativeTimeSpanString(getContext(), article.getDate().getTime());

        title.setText(article.getTitle());
        author.setText(getString(R.string.article_subtitle, date, article.getAuthor()));
        content.setText(Html.fromHtml(article.getContent()).toString());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_menu:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, article.getTitle(), article.getUrl()));
                Intent chooser = Intent.createChooser(shareIntent, getString(R.string.share));
                startActivity(chooser);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}