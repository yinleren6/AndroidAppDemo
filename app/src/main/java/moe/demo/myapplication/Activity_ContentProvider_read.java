package moe.demo.myapplication;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Activity_ContentProvider_read extends BaseActivity {


    ArrayAdapter<String> mAdapter;
    List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_read);
        setTitle("内容提供器 ContentProvider");
        ListView mView = findViewById(R.id.phonelist);
        mAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mList);
        mView.setAdapter(mAdapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        else {
            read();
        }
    }


    //添加数据
    void a() {
        Uri uri = null;
        ContentValues values = new ContentValues();
        values.put("column1", "text");
        getContentResolver().insert(uri, values);

    }

    //更新数据
    void b() {
        Uri uri = null;
        ContentValues values = new ContentValues();
        values.put("column1", "text");
        getContentResolver().update(uri, values, "column1=? and column2=?", new String[]{"text", "1"});

    }//删除数据

    void c() {
        Uri uri = null;
        getContentResolver().delete(uri, "column1=?", new String[]{"1"});

    }


    //查询
    private void read() {
        Cursor cursor;

        ///////////////////////////////
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    mList.add(name + "\t" + number);
                }
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        } finally {
            cursor.close();
        }
    }

    //////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    read();

                }
                else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    //    }   private void read() {
    //        String[] projection = {};
    //        String selection = "";
    //        String[] selectionArgs = {};
    //        String sortOrder = "";
    //        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    //        if (cursor != null) {
    //            while (cursor.moveToNext()) {
    //                String column1 = cursor.getString(cursor.getColumnIndex("column1"));
    //                int column2 = cursor.getInt(cursor.getColumnIndex("column1"));
    //            }
    //            cursor.close();
    //        }
    //    }

}