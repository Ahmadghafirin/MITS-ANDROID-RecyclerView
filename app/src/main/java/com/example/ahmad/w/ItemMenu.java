package com.example.ahmad.w;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmad on 13/07/17.
 */

public class ItemMenu implements Parcelable {
    private String menu, harga, keterangan;
    private int icon;

    public ItemMenu(String menu, String harga, String keterangan, int icon) {
        this.menu = menu;
        this.harga = harga;
        this.keterangan = keterangan;
        this.icon = icon;
    }

    public ItemMenu() {
    }

    protected ItemMenu(Parcel in) {
        menu = in.readString();
        harga = in.readString();
        keterangan = in.readString();
        icon = in.readInt();
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

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        dest.writeString(harga);
        dest.writeString(keterangan);
        dest.writeInt(icon);
    }

    @Override
    public String toString() {
        return "ItemMenu{" +
                "menu='" + menu + '\'' +
                ", harga='" + harga + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", icon=" + icon +
                '}';
    }
}
