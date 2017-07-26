package com.example.ahmad.w.menu;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmad on 13/07/17.
 */

public class ItemMenu implements Parcelable {
    private String menu, price, details, image;
    private int id;

    public ItemMenu(int id, String menu, String price, String details, String image) {
        this.id = id;
        this.menu = menu;
        this.price = price;
        this.details = details;
        this.image = image;
    }

    public ItemMenu() {
    }

    public ItemMenu(Parcel in) {
        menu = in.readString();
        price = in.readString();
        details = in.readString();
        image = in.readString();
        id = in.readInt();
    }

    public static final Creator<ItemMenu> CREATOR = new Creator<ItemMenu>() {
        @Override
        public ItemMenu createFromParcel(Parcel in) {
            return new ItemMenu(in);
        }

        @Override
        public ItemMenu[] newArray(int size) {
            return new ItemMenu[size];
        }
    };

    public ItemMenu(String name, String price, String details, String path) {

    }

    public ItemMenu(int id, String menu, String price, String details, int image) {
    }

    public String getMenu() {
        return menu;
    }

    public void setName(String menu) {
        this.menu = menu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String harga) {
        this.price = harga;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String icon) {
        this.image = icon;
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
        dest.writeString(menu);
        dest.writeString(price);
        dest.writeString(details);
        dest.writeString(image);
        dest.writeInt(id);
    }

    @Override
    public String toString() {
        return "ItemMenu{" +
                "menu='" + menu + '\'' +
                ", price='" + price + '\'' +
                ", details='" + details + '\'' +
                ", image=" + image +
                '}';
    }
}
