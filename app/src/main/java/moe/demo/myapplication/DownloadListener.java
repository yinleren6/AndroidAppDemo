package moe.demo.myapplication;

public interface DownloadListener {


    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused(int progress);

    void onCanceled();


}
