package moe.demo.myapplication;

/**
 * 自定义列表项
 * 可以显示一个图片
 * 和一个文本
 */
public class ListItem1 {
    private final int imageId;
    private final String text1;

    public ListItem1(int imageId, String text1) {
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