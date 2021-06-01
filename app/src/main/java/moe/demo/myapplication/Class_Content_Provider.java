package moe.demo.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;

public class Class_Content_Provider extends ContentProvider {

    private static final int ITEM = 1;
    private static final int ALL = 2;
    static String AUTHORITY = "moe.demo.myapplication.provider";
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "data003/#", ALL);
        uriMatcher.addURI(AUTHORITY, "data003", ITEM);
    }

    private SqliteHelperSelf mSqliteHelper;

    @Override
    public boolean onCreate() {
        mSqliteHelper = new SqliteHelperSelf(getContext(), "data3.db", null, 2);
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        Cursor cursor = null;

        //查询所有数据
        if (uriMatcher.match(uri) == ALL) {

            cursor = db.query("data003", projection, selection, selectionArgs, null, null, sortOrder);

        }
        //查询一条数据
        else if (uriMatcher.match(uri) == ITEM) {

            String id = uri.getPathSegments().get(1);
            cursor = db.query("data003", projection, "id=?", new String[]{id}, null, null, sortOrder);
        }
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        Uri uriReturn = null;
        //添加数据
        if (uriMatcher.match(uri) == ALL || uriMatcher.match(uri) == ITEM) {


            Long id = db.insert("data003", null, values);
            uriReturn = Uri.parse("content://" + AUTHORITY + "/data003/" + id);
        }

        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        int row = 0;
        //更新所有
        if (uriMatcher.match(uri) == ALL) {

            row = db.update("data003", values, selection, selectionArgs);
        }
        //更新一条
        else if (uriMatcher.match(uri) == ITEM) {
            String id = uri.getPathSegments().get(1);
            row = db.update("data003", values, "id=?", new String[]{id});
        }
        return row;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        int row = 0;
        //删除全部
        if (uriMatcher.match(uri) == ALL) {

            row = db.delete("data003", selection, selectionArgs);
        }
        //删除一条
        else if (uriMatcher.match(uri) == ITEM) {
            String id = uri.getPathSegments().get(1);
            row = db.delete("data003", "id=?", new String[]{id});
        }
        return row;

    }

    @Override
    public String getType(Uri uri) {
        if (uriMatcher.match(uri) == ALL) {
            return "vnd.android.cursor.dir/moe.demo.myapplication.provider.data003";

        }
        //删除一条
        else if (uriMatcher.match(uri) == ITEM) {
            return "vnd.android.cursor.item/moe.demo.myapplication.provider.data003";
        }

        return null;
    }
}
