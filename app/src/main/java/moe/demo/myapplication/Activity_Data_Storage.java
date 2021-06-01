package moe.demo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;


public class Activity_Data_Storage extends AppCompatActivity {

    private Button f1, f2, f3, p1, p2, sqlite;
    private TextView textView, textView2;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        init();


    }

    private void init() {
        f1 = findViewById(R.id.b_read_file);
        f2 = findViewById(R.id.b_w_p);
        f3 = findViewById(R.id.b_w_a);
        p1 = findViewById(R.id.b_read_pr);
        p2 = findViewById(R.id.b_w_pr);
        sqlite = findViewById(R.id.b_sql);


        textView = findViewById(R.id.textView50);
        textView2 = findViewById(R.id.textView54);
        View.OnClickListener listener = v -> {
            int id = v.getId();
            Button b = (Button) v;
            String c = (String) b.getText();
            Toast.makeText(this, "按钮 " + c, Toast.LENGTH_LONG).show();

            if (f1.getId() == id) {
                read1();
                Toast.makeText(this, "按钮 1  " + c, Toast.LENGTH_LONG).show();
            } else if (f2.getId() == id) {
                save1(Context.MODE_PRIVATE);
                Toast.makeText(this, "按钮 2  " + c, Toast.LENGTH_LONG).show();
            } else if (f3.getId() == id) {
                save1(Context.MODE_APPEND);
                Toast.makeText(this, "按钮 3  " + c, Toast.LENGTH_LONG).show();
            } else if (p1.getId() == id) {
                read2();
                Toast.makeText(this, "按钮 4  " + c, Toast.LENGTH_LONG).show();
            } else if (p2.getId() == id) {
                save2();
                Toast.makeText(this, "按钮 5  " + c, Toast.LENGTH_LONG).show();
            } else if (sqlite.getId() == id) {
                Intent intent = new Intent(this, Activity_Data_Storage_SQLite.class);
                startActivity(intent);
                finish();
            }
        };
        f1.setOnClickListener(listener);
        f2.setOnClickListener(listener);
        f3.setOnClickListener(listener);
        p1.setOnClickListener(listener);
        p2.setOnClickListener(listener);
        sqlite.setOnClickListener(listener);
    }


    private void read1() {

        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {

            in = openFileInput("data1");

            reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                textView.setText(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void save1(int mode) {
        String data = "123";
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            //得到一个 FileOutputStream 对象
            out = openFileOutput("data1", mode);
            //构造一个 BufferedWriter 对象
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //写入内容
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read2() {
        preferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String db_id = preferences.getString("name", "null");
        String db_pw = preferences.getString("value", "0");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
//        String a = simpleDateFormat.format(db_pw);
        textView2.setText(db_id + "\n" + "\n" + db_pw);
    }

    private void save2() {
        preferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        preferences.edit().putString("name", "时间戳").putString("value", String.valueOf(System.currentTimeMillis() / 1000)).apply();
        Toast.makeText(this, new Date().toString(), Toast.LENGTH_LONG).show();
    }

}