package buttasam.cvut.cz.android_feedreader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import buttasam.cvut.cz.android_feedreader.activity.AddFeedResourceActivity;
import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.model.Feed;
import buttasam.cvut.cz.android_feedreader.service.FeedMockService;
import buttasam.cvut.cz.android_feedreader.service.FeedService;


public class FeedFragment extends Fragment {

    private FeedService feedService = FeedMockService.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_feed, container, false);

        for(Feed feed : feedService.allFeeds()) {
            View view = inflater.inflate(R.layout.feed_preview, container);
            TextView title = view.findViewById(R.id.feed_preview_title);
            title.setText(feed.getTitle());
            TextView url= view.findViewById(R.id.feed_preview_url);
            url.setText(feed.getUrl());

            layout.addView(view);
        }

        return layout;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.feed, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_feed:
                startActivity(new Intent(getContext(), AddFeedResourceActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
