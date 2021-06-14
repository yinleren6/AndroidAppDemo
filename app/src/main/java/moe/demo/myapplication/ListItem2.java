package moe.demo.myapplication;

/**
 * 自定义列表项
 * 可以显示一个图片
 * 和一个主要文本
 * 和一个副文本
 */
public class ListItem2 {

    private String t1;
    private String t2;
    private int imageId;

    public ListItem2(String t1, String t2, int imageId) {
        this.t1 = t1;
        this.t2 = t2;
        this.imageId = imageId;
    }

    public String getTitle() {
        return t1;
    }

    public String getText() {
        return t2;
    }

    public int getImageId() {
        return imageId;
    }


}
