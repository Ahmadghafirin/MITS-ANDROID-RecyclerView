package com.example.ahmad.w.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

/**
 * Created by ahmad on 13/07/17.
 */


@Table(name = "ItemMenu")
public class ItemMenu extends Model implements Parcelable {

    @Column(name = "Menu")
    private String menu;

    @Column(name = "Price")
    private int price;

    @Column(name = "Details")
    private String details;

    @Column(name = "Image")
    private String image;

    public ItemMenu() {
        super();
    }


    public ItemMenu(String nameMenu, int price, String details, String image) {
        super();
        this.menu = nameMenu;
        this.price = price;
        this.details = details;
        this.image = image;
    }

    public ItemMenu(Parcel in) {
        menu = in.readString();
        price = in.readInt();
        details = in.readString();
        image = in.readString();
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


    public String getMenu() {
        return menu;
    }

    public void setName(String menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int harga) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu);
        dest.writeInt(price);
        dest.writeString(details);
        dest.writeString(image);
    }

    @Override
    public String toString() {
        return "ItemMenu{" +
                "menu='" + menu + '\'' +
                ", price='Rp." + price + '\'' +
                ", details='" + details + '\'' +
                ", image=" + image +
                '}';
    }

    public static List<ItemMenu> getAllMenu() {
        return new Select().from(ItemMenu.class)
                .orderBy("Id Desc").execute();
    }

    public static void updateMenu(long id, ItemMenu itemMenu) {
        new Update(ItemMenu.class).set("Menu = ?, Price = ?, Details = ?, Image = ?",
                itemMenu.getMenu(), itemMenu.getPrice(), itemMenu.getDetails(), itemMenu.getImage())
                .where("Id = ?", id).execute();
    }
}
