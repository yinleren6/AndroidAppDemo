package moe.demo.myapplication;

import android.app.Application;
import android.content.Context;

/**
 *获取一个 全局Context 对象
 * 需要在清单注册
 *用法 ； MyContext.getContext()
 */
public class MyContext extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
