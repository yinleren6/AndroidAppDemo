package moe.demo.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class SqliteHelper extends SQLiteOpenHelper {


    String thisSql = "create table data003("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "price integer)";
    ;
    Context thisContext;

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        thisContext = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(thisSql);
        Toast.makeText(thisContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(thisContext, "更新成功", Toast.LENGTH_SHORT).show();
    }


}
