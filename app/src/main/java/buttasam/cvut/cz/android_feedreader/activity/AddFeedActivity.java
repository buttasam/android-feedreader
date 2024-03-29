package buttasam.cvut.cz.android_feedreader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.model.Feed;
import buttasam.cvut.cz.android_feedreader.service.FeedMockService;
import buttasam.cvut.cz.android_feedreader.service.FeedService;

public class AddFeedActivity extends AppCompatActivity {

    private FeedService feedService = FeedMockService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_resource);
    }

    public void addFeedResource(View view) {

        EditText editTitle = findViewById(R.id.text_edit_add_resource_title);
        EditText editUrl = findViewById(R.id.text_edit_add_resource_url);
        String title = editTitle.getText().toString();
        String url = editUrl.getText().toString();

        feedService.saveFeed(new Feed(feedService.allFeeds().size() + 1, title, url)); // FIXME - save to db

        startActivity(new Intent(this, FeedActivity.class));
    }
}
