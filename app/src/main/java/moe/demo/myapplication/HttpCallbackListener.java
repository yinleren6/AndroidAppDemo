package moe.demo.myapplication;

public interface HttpCallbackListener {
    void OnFinish(String response);

    void OnError(Exception e);
}
