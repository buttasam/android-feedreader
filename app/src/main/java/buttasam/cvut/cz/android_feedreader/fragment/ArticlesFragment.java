package buttasam.cvut.cz.android_feedreader.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.activity.FeedActivity;
import buttasam.cvut.cz.android_feedreader.api.FeedReaderTask;
import buttasam.cvut.cz.android_feedreader.database.ArticleTable;
import buttasam.cvut.cz.android_feedreader.database.ReaderContentProvider;
import buttasam.cvut.cz.android_feedreader.fragment.adapter.ArticleCursorAdapter;
import buttasam.cvut.cz.android_feedreader.service.ArticleService;
import buttasam.cvut.cz.android_feedreader.service.ArticleServiceImpl;


public class ArticlesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ArticleService articleService;
    private final int ARTICLE_LOADER = 1;

    private ListView mListView;
    private ArticleCursorAdapter mAdapter;
    private MenuItem refreshMenuItem;
    private View mProgressActionView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressActionView = LayoutInflater.from(getActivity()).inflate(R.layout.action_view_progress,
                null);

        setRetainInstance(true);
        setHasOptionsMenu(true);

        this.articleService = new ArticleServiceImpl(getActivity().getContentResolver());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getActivity().findViewById(R.id.articles_content);
        mAdapter = new ArticleCursorAdapter(getActivity(), null, 0);
        mListView.setAdapter(mAdapter);

        getLoaderManager().initLoader(ARTICLE_LOADER, null, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        switch (id) {
            case ARTICLE_LOADER:
                return new CursorLoader(getActivity(), ReaderContentProvider.CONTENT_URI,
                        new String[]{ArticleTable.ID, ArticleTable.TITLE, ArticleTable.AUTHOR, ArticleTable.DATE, ArticleTable.CONTENT, ArticleTable.URL},
                        null, null, null);
            default:
                break;
        }

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case ARTICLE_LOADER:
                mAdapter.swapCursor(cursor);
                break;

            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        switch (loader.getId()) {
            case ARTICLE_LOADER:
                mAdapter.swapCursor(null);
                break;

            default:
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.articles, menu);
        refreshMenuItem = menu.findItem(R.id.reload_menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload_menu:
                refreshFeed();
                return true;
            case R.id.menu_configure_feeds:
                startActivity(new Intent(getContext(), FeedActivity.class));
                return true;
            case R.id.menu_delete_articles:
                articleService.deleteArticles();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refreshFeed() {
        FeedReaderTask task = new FeedReaderTask(articleService, this.refreshMenuItem, mProgressActionView);
        task.execute();
    }

}
