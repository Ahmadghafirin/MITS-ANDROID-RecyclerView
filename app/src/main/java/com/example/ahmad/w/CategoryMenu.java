package com.example.ahmad.w;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CategoryMenu implements Parcelable {

    private String menu;
    private int icon;

    public CategoryMenu(String menu, int icon) {
        this.menu = menu;
        this.icon = icon;
    }

    protected CategoryMenu(Parcel in) {
        menu = in.readString();
        icon = in.readInt();
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu);
        dest.writeInt(icon);
    }

    @Override
    public String toString() {
        return "CategoryMenu{" +
                "menu='" + menu + '\'' +
                ", icon=" + icon +
                '}';
    }
}
