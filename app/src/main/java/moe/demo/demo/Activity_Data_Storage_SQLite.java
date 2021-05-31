package moe.demo.demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_Data_Storage_SQLite extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TAG_Activity_SQLite";
    Button insert, upgrade, delete, query, ix, ux, dx, qx;
    EditText mText11, mText12, mText13, mText21, mText22, mText23, mText31, mText41;
    TextView textView;

    SqliteHelper helper = new SqliteHelper(this, "data3.db", null, 1);
    Toast mToast;

    public Activity_Data_Storage_SQLite() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        insert = findViewById(R.id.b_insert);
        upgrade = findViewById(R.id.b_upgrade);
        delete = findViewById(R.id.b_delete);
        query = findViewById(R.id.b_query);
        ix = findViewById(R.id.b_1x);
        ux = findViewById(R.id.b_2x);
        dx = findViewById(R.id.b_3x);
        qx = findViewById(R.id.b_4x);
        mText11 = findViewById(R.id.e11);
        mText12 = findViewById(R.id.e12);
        mText13 = findViewById(R.id.e13);
        mText21 = findViewById(R.id.e21);
        mText22 = findViewById(R.id.e22);
        mText23 = findViewById(R.id.e23);
        mText31 = findViewById(R.id.e31);
        mText41 = findViewById(R.id.e41);
        textView = findViewById(R.id.textView13);

        insert.setOnClickListener(this);
        upgrade.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);
        ix.setOnClickListener(this);
        ux.setOnClickListener(this);
        dx.setOnClickListener(this);
        qx.setOnClickListener(this);

        mToast = new Toast(this);
    }

    @Override
    public void onClick(View v) {
        if (mToast != null) {
            mToast.cancel();
        }


        if (v.getId() == R.id.b_insert) {
//            插入

            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", mText11.getText().toString());
            values.put("name", mText12.getText().toString());
            values.put("price", mText13.getText().toString());
            db.insert("data003", null, values);
            values.clear();
            long row = db.insert("data003", null, values);
            mToast.makeText(this, "插入 finished with code \"" + row + "\"", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "finished with code \"" + row + "\"");
        } else if (v.getId() == R.id.b_upgrade) {
            //更新
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (mText21.getText() != null) {

                values.put("id", mText21.getText().toString());


            }
            if (mText22.getText() != null) {
                values.put("name", mText22.getText().toString());
            }
            if (mText23.getText() != null) {
                values.put("price", mText23.getText().toString());
            }

            int row = db.update("data003", values, "id=?", new String[]{mText21.getText().toString()});
            values.clear();
            mToast.makeText(this, "更新 finished with code \"" + row + "\"", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "finished with code \"" + row + "\"");

            mToast.makeText(this, "无更新 ", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.b_delete) {
            //删除
            String id = mText31.getText().toString();
            SQLiteDatabase db = helper.getWritableDatabase();
            int row = db.delete("data003", "id=?", new String[]{id});
            mToast.makeText(this, "删除 finished with code \"" + row + "\"", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "finished with code \"" + row + "\"");
        } else if (v.getId() == R.id.b_query) {
            //查询
            textView.setText(null);
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.query("data003", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int price = cursor.getInt(cursor.getColumnIndex("price"));
                    Log.d(TAG, "-\nid -> " + id + "\tname -> " + name + "\tprice -> " + price);
                    textView.setText(textView.getText() + "-\tid -> " + id + "\tname -> " + name + "\tprice -> " + price + "\n");
                } while (cursor.moveToNext());
                cursor.close();
                mToast.makeText(this, "查询 finished", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.b_1x) {
            mText11.setText(null);
            mText12.setText(null);
            mText13.setText(null);
        } else if (v.getId() == R.id.b_2x) {
            mText21.setText(null);
            mText22.setText(null);
            mText23.setText(null);
        } else if (v.getId() == R.id.b_3x) {
            mText31.setText(null);
        } else if (v.getId() == R.id.b_4x) {
            mText41.setText(null);
        }
    }
}
