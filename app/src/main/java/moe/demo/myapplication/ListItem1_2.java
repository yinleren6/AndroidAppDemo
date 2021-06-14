package moe.demo.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 自定义列表项
 * 可以显示一个图片
 * 和一个文本
 * ------------------------
 * 添加了实现 Parcelable 接口，可以将一个完整的对象分解，分解后的每一部分都是 Intent 所支持的数据类型
 * 用法：new 一个实例 listitem1 然后 intent.putExtra("name", listitem1) 放到 intent 中
 * 获取：new 一个实例 listitem1 =(ListItem1) getIntent().getParcelableExtra("name")
 * 这样就实现了传递对象的功能
 */
public class ListItem1_2 implements Parcelable {

    private String text1;
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return text1;
    }


    public void setText1(String text1) {
        this.text1 = text1;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(text1);
    }




    public static final Creator<ListItem1_2> CREATOR = new Creator<ListItem1_2>() {

        @Override
        public ListItem1_2 createFromParcel(Parcel in) {

            ListItem1_2 listItem1_2 = new ListItem1_2();
            listItem1_2.imageId = in.readInt();
            listItem1_2.text1 = in.readString();

            return listItem1_2;
        }

        @Override
        public ListItem1_2[] newArray(int size) {
            return new ListItem1_2[size];
        }
    };


}