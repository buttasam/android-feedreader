package buttasam.cvut.cz.android_feedreader.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ReaderContentProvider extends ContentProvider {

	private ReaderDatabaseHelper mDbHelper;

	public static final String AUTHORITY = "cz.cvut.fitmag";

	private static final int ARTICLE_LIST = 1;
	private static final int ARTICLE_ID = 2;

	private static final String BASE_PATH = "articles";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, ARTICLE_LIST);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ARTICLE_ID);
	}

	@Override
	public boolean onCreate() {
		mDbHelper = new ReaderDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case ARTICLE_LIST:
			queryBuilder.setTables(ArticleTable.TABLE_ARTICLE);
			break;
		case ARTICLE_ID:
			queryBuilder.setTables(ArticleTable.TABLE_ARTICLE);
			queryBuilder.appendWhere(ArticleTable.ID + "=" + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
		long id;
		switch (uriType) {
		case ARTICLE_LIST:
			id = sqlDB.insert(ArticleTable.TABLE_ARTICLE, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
		int rowsDeleted;
		switch (uriType) {
		case ARTICLE_LIST:
			rowsDeleted = sqlDB.delete(ArticleTable.TABLE_ARTICLE, selection, selectionArgs);
			break;
		case ARTICLE_ID:
			String id = uri.getLastPathSegment();
			rowsDeleted = sqlDB.delete(ArticleTable.TABLE_ARTICLE, ArticleTable.ID + "=" + id, null);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

}
