package buttasam.cvut.cz.android_feedreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import buttasam.cvut.cz.android_feedreader.activity.FeedActivity;

public class AddFeedResourceActivity extends AppCompatActivity {

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

        System.out.println(title);
        System.out.println(url);

        startActivity(new Intent(this, FeedActivity.class));
    }
}
