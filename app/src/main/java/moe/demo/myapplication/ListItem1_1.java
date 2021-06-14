package moe.demo.myapplication;

import java.io.Serializable;

/**
 * 自定义列表项
 * 可以显示一个图片
 * 和一个文本
 * ------------------------
 * 添加了实现 Serializable 接口，可以将此类序列化，能将对象转换成可存储或可传输状态
 * 用法：new 一个实例 listitem1 然后 intent.putExtra("name", listitem1) 放到 intent 中
 * 获取：new 一个实例 listitem1 =(ListItem1) getIntent().getSerializableExtra("name")
 * 这样就实现了传递对象的功能
 */
public class ListItem1_1 implements Serializable {
    private final int imageId;
    private final String text1;

    public ListItem1_1(int imageId, String text1) {
        this.imageId = imageId;
        this.text1 = text1;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return text1;
    }
}