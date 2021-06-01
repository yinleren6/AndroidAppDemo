package moe.demo.myapplication;

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
