package com.example.ahmad.w.categorymenu;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryMenu implements Parcelable {

    private String name;
    private int image, id;

    public CategoryMenu(String name, int image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public CategoryMenu() {
    }

    protected CategoryMenu(Parcel in) {
        name = in.readString();
        image = in.readInt();
        id = in.readInt();
    }

    public static final Creator<CategoryMenu> CREATOR = new Creator<CategoryMenu>() {
        @Override
        public CategoryMenu createFromParcel(Parcel in) {
            return new CategoryMenu(in);
        }

        @Override
        public CategoryMenu[] newArray(int size) {
            return new CategoryMenu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeInt(id);
    }

    @Override
    public String toString() {
        return "CategoryMenu{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
