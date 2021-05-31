package moe.demo.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class SqliteHelper extends SQLiteOpenHelper {

    private static final String TAG = "TAG_SqliteHelper";
    String thisSql1 = "create table data003(id integer primary key autoincrement, name text, price integer)";
    String thisSql2 = "create table newtable(" + "id integer primary key autoincrement," + "name text," + "price integer)";

    Context thisContext;

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        thisContext = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(thisSql1);
        Log.d(TAG, "db.execSQL(thisSql1)");

        db.execSQL(thisSql2);
        Log.d(TAG, "db.execSQL(thisSql2)");

        Toast.makeText(thisContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists data003");
        Log.d(TAG, "删除了data003");
        Toast.makeText(thisContext, "更新成功", Toast.LENGTH_SHORT).show();
    }


}
