package buttasam.cvut.cz.android_feedreader.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import buttasam.cvut.cz.android_feedreader.R;
import buttasam.cvut.cz.android_feedreader.activity.ArticleDetailActivity;
import buttasam.cvut.cz.android_feedreader.database.ArticleTable;

public class ArticleCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	
	public ArticleCursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mInflater.inflate(R.layout.article_preview, parent, false);
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		((TextView) view.findViewById(R.id.preview_title)).setText(cursor.getString(cursor.getColumnIndex(ArticleTable.TITLE)));

		int articleId = cursor.getInt(cursor.getColumnIndex(ArticleTable.ID));
		view.setOnClickListener(new ArticleOnClickListener(articleId));
	}

	public class ArticleOnClickListener implements View.OnClickListener {

		private long articleId;

		ArticleOnClickListener(long articleId) {
			this.articleId = articleId;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext, ArticleDetailActivity.class);
			intent.putExtra(ArticleDetailActivity.ARTICLE_ID, articleId);
			mContext.startActivity(intent);
		}
	}
}
