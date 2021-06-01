package moe.demo.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteHelperSelf extends SQLiteOpenHelper {

    private static final String TAG = "TAG_SqliteHelper";
    String thisSql1 = "create table data003(id integer primary key autoincrement, name text, price integer)";


    Context thisContext;

    public SqliteHelperSelf(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        thisContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(thisSql1);
        Log.d(TAG, "db.execSQL(thisSql1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists data003");
        Log.d(TAG, "删除了data003");

    }


}
