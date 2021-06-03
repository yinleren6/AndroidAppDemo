package moe.demo.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Activity_CameraAlbum extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TAG_Activity_CameraAlbum";
    private static final int CAMERA = 1;
    private static final int Album = 2;
    private static final int FILE = 3;
    private ImageView mImageView;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_album);
        findViewById(R.id.button22).setOnClickListener(this);
        findViewById(R.id.button25).setOnClickListener(this);
        findViewById(R.id.button27).setOnClickListener(this);
        findViewById(R.id.button32).setOnClickListener(this);
        mImageView = findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {

        //1.启动相机拍照
        if (v.getId() == R.id.button22) {
            Log.i(TAG, "启动相机");
            //创建 File 对象 用于存放文件
            File image = new File(getExternalCacheDir(), "output.jpg");
            //如果文件存在就先删除，在创建
            try {
                if (image.exists()) {
                    image.delete();
                }
                //创建output.jpg图片文件
                image.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //判断sdk版本
            if (Build.VERSION.SDK_INT >= 24) {
                //获取文件的URI
                mUri = FileProvider.getUriForFile(Activity_CameraAlbum.this, "moe.demo.myapplication.fileprovider", image);
            }
            //获取文件的URI
            else {
                mUri = Uri.fromFile(image);
            }
            //启动相机 把拍摄的照片URI用Intent传输
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            //指定拍完照片的输出URI路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(intent, CAMERA);
        }

        //2.从系统相册选择照片
        else if (v.getId() == R.id.button25) {

            if (ContextCompat.checkSelfPermission(Activity_CameraAlbum.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity_CameraAlbum.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
            }
            else {


                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); //允许多选

                Log.i(TAG, "启动系统相册");
                startActivityForResult(intent, Album);
            }
        }
        //TODO 2.1选择并裁剪
        else if (v.getId() == R.id.button32) {
            Log.i(TAG, "TODO");
            Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();
        }
        //3.从文件选择
        else if (v.getId() == R.id.button27) {
            if (ContextCompat.checkSelfPermission(Activity_CameraAlbum.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity_CameraAlbum.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
            }
            else {

                Log.i(TAG, "启动文件管理");
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, FILE);
            }
        }

    }

    String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        //1.回调 拍照
        if (requestCode == CAMERA) {
            Log.i(TAG, "回调 拍照");
            Log.i(TAG, (resultCode == RESULT_OK) + "");
            Toast.makeText(this, "拍照设置图片", Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                //用文件输入流创建bitmap 对象并设置imageView
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mUri));
                    Log.i(TAG, "设置ImageView");
                    setImage(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        //2.从相册选择 https://blog.csdn.net/Code_legend/article/details/77620359
        //TODO MIUI 相册无法获取
        else if (requestCode == Album) {
            Log.i(TAG, "回调 相册选择");
            Log.i(TAG, "resultCode: [" + resultCode + "] = RESULT_OK : [" + (resultCode == RESULT_OK) + "]");

            if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                try {
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri

                    Log.i(TAG, "selectedImage" + selectedImage);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    //从系统表中查询指定Uri对应的照片
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //获取照片路径
                    String path = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Log.i(TAG, "设置ImageView");
                    setImage(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //3.从文件选择
        else if (requestCode == FILE) {
            Log.i(TAG, "回调 从文件选择 " + (resultCode == RESULT_OK) + "");

            if (resultCode == RESULT_OK) {

                String imagePath = null;
                Uri uri = data.getData();

                Log.i(TAG, "URI= " + uri.toString());
                Log.i(TAG, "Authority= " + uri.getAuthority());
                Log.i(TAG, "com.miui.gallery.open".equals(uri.getAuthority()) + "");

                if ("com.miui.gallery.open".equals(uri.getAuthority())) {
                    //MIUI 相册
                    Log.i(TAG, "MIUI1");

                    imagePath = uri.getPath().split("/raw/")[1];

                }
                if (DocumentsContract.isDocumentUri(this, uri)) {

                    String docId = DocumentsContract.getDocumentId(uri);


                    //1.1 MediaDocument
                    if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        Log.i(TAG, "com.android.providers.media.documents媒体照片");
                        //解析数字格式id
                        String id = docId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    }

                    //1.2 DownloadsDocument
                    else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        Log.i(TAG, "com.android.providers.downloads.documents下载");
                        //TODO 摸索出来
                        Log.i(TAG, docId);
                        //                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));

                        //                        Log.i(TAG, contentUri.toString());
                        imagePath = uri.getPath().split(":")[1];
                    }

                    //1.3 ExternalStorageProvider
                    else if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                        Log.i(TAG, "com.android.externalstorage.documents外部存储");
                        //TODO 摸索出来

                        imagePath = Environment.getExternalStorageDirectory().getPath() + "/" + docId.split(":")[1];
                    }

                    //2 MediaStore general
                    else if ("content".equals(uri.getAuthority())) {
                        Log.i(TAG, "content");

                        imagePath = getImagePath(uri, null);
                    }

                    //3
                    else if ("file".equals(uri.getAuthority())) {
                        Log.i(TAG, "file");
                        imagePath = uri.getPath();

                    }
                    //https://blog.csdn.net/smileiam/article/details/79753745

                }

                Log.i(TAG, "ImagePath: " + imagePath);
                setImage(imagePath);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置inageVView
    void setImage(Bitmap mBitmap) {
        mImageView.setImageBitmap(mBitmap);
    }

    void setImage(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        mImageView.setImageBitmap(bitmap);
    }

    //申请权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
            else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}