package moe.demo.demo;

public class Class_ListView_Item {
    private String t1;
    private String t2;
    private int imageId;

    public Class_ListView_Item(String t1, String t2, int imageId) {
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
