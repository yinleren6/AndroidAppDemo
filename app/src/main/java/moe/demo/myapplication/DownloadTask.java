package moe.demo.myapplication;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends android.os.AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;
    private static final String TAG = "TAG_DownloadTask";
    private final DownloadListener listener;
    File file = null;
    String fileName;
    String downloadUrl;
    String directory;
    int progress;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.i(TAG, "onPreExecute");
        //准备工作
        //如：显示进度条
        //设置下载路径
        directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    @Override
    protected Integer doInBackground(String... params) {
        Log.i(TAG, "doInBackground");
        //执行耗时任务  如下载
        InputStream in = null;
        RandomAccessFile savedFile = null;

        try {
            long downloadLength = 0; //记录已下载的文件的长度
            downloadUrl = params[0];
            fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));

            file = new File(directory + fileName);
            if (file.exists()) {
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            }
            else if (contentLength == downloadLength) {
                //已下载字节和文件总字节相等 说明下完了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()//
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")//断点下载 指定从那个字节开始下载
                    .url(downloadUrl)//
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                in = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = in.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    }
                    else if (isPaused) {
                        return TYPE_PAUSED;
                    }
                    else {
                        total += len;
                        savedFile.write(b, 0, len);
                        //计算已下载百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        super.onPostExecute(status);
        Log.i(TAG, "onPostExecute");
        //收尾工作
        //如：关闭进度条 显示结果
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused(progress);
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pause() {
        isPaused = true;
    }

    public void calcel() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()//
                .url(downloadUrl)//
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLengtth = response.body().contentLength();
            response.close();
            return contentLengtth;
        }
        return 0;
    }
}
