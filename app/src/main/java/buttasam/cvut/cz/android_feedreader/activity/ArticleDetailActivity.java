package buttasam.cvut.cz.android_feedreader.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.fragment.ArticleDetailFragment;

public class ArticleDetailActivity extends AppCompatActivity {

    public static final String ARTICLE_ID = "ARTICLE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.frame) == null) {
            FragmentTransaction transaction = manager.beginTransaction();

            Bundle args = new Bundle();
            args.putLong(ArticleDetailFragment.ARTICLE_ID, getIntent().getLongExtra(ARTICLE_ID, 0));

            transaction.add(R.id.frame, Fragment.instantiate(this, ArticleDetailFragment.class.getName(), args));
            transaction.commit();
        }
    }


}
