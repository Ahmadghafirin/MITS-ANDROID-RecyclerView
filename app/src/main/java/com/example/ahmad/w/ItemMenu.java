package com.example.ahmad.w;

/**
 * Created by ahmad on 13/07/17.
 */

public class ItemMenu {
    private String menu, harga;
    private int icon;

    public ItemMenu(String menu, String harga, int icon) {
        this.menu = menu;
        this.harga = harga;
        this.icon = icon;
    }

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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


}
