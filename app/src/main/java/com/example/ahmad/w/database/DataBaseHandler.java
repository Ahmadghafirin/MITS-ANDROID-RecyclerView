package com.example.ahmad.w.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ahmad.w.categorymenu.CategoryMenu;
import com.example.ahmad.w.menu.ItemMenu;
import com.example.ahmad.w.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 23/07/17.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "db_menu";

    private static final String TABLE_MENU = "menu";
    private static final String KEY_ID_MENU = "id_menu";
    private static final String KEY_NAMEMENU = "name_menu";
    private static final String KEY_PRICEMENU = "price_menu";
    private static final String KEY_IMAGEMENU = "image_menu";
    public static final String KEY_DETAILSMENU = "details_menu";

  /*  private static final String TABLE_CATEGORY = "category";
    private static final String KEY_ID_CATEGORY = "id_category";
    private static final String KEY_NAMECATEGORY = "name_category";
    private static final String KEY_IMAGECATEGORY = "image_category";*/

    private static final String TABLE_USER = "user";
    private static final String KEY_ID_USER = "id_user";
    private static final String KEY_NAMEUSER = "name_user";
    private static final String KEY_EMAILUSER = "name_email";
    private static final String KEY_PHONEUSER = "name_phone";
    private static final String KEY_GENDERUSER = "name_gender";
    private static final String KEY_PASSWORDUSER = "name_pass";


    public static final String TAG = "tag database";

    private static SQLiteDatabase db;
    private static DataBaseHandler instance;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void init(Context context) {
        instance = new DataBaseHandler(context);
        db = instance.getWritableDatabase();
    }

    public static synchronized DataBaseHandler getInstance() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MENU_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MENU + "("
                + KEY_ID_MENU + " INTEGER PRIMARY KEY,"
                + KEY_NAMEMENU + " TEXT,"
                + KEY_PRICEMENU + " TEXT,"
                + KEY_DETAILSMENU + " TEXT,"
                + KEY_IMAGEMENU + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_MENU_TABLE);

        /*String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_ID_CATEGORY + " INTEGER PRIMARY KEY,"
                + KEY_NAMECATEGORY + " TEXT,"
                + KEY_IMAGECATEGORY + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CATEGORY_TABLE);*/

        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + KEY_ID_USER + " INTEGER PRIMARY KEY,"
                + KEY_NAMEUSER + " TEXT,"
                + KEY_EMAILUSER + " TEXT,"
                + KEY_PHONEUSER + " TEXT,"
                + KEY_GENDERUSER + " TEXT,"
                + KEY_PASSWORDUSER + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addMenu(ItemMenu itemMenu) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMEMENU, itemMenu.getMenu());
        values.put(KEY_PRICEMENU, itemMenu.getPrice());
        values.put(KEY_IMAGEMENU, itemMenu.getImage());
        values.put(KEY_DETAILSMENU, itemMenu.getDetails());

        db.insert(TABLE_MENU, null, values);
        Log.d(TAG, "Add ItemMenu Succes!");
    }

    /*public void addCategory(CategoryMenu categoryMenu) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMECATEGORY, categoryMenu.getName());
        values.put(KEY_ID_CATEGORY, categoryMenu.getImage());

        db.insert(TABLE_CATEGORY, null, values);
        Log.d(TAG, "Add Category Succes!");
    }*/

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMEUSER, user.getName());
        values.put(KEY_EMAILUSER, user.getEmail());
        values.put(KEY_PHONEUSER, user.getPhone());
        values.put(KEY_GENDERUSER, user.getGender());
        values.put(KEY_PASSWORDUSER, user.getPassword());

        db.insert(TABLE_USER, null, values);
        Log.d(TAG, "Add data Succes !");
    }

    public List<ItemMenu> getAllMenu() {
        List<ItemMenu> itemMenuList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_MENU + " ORDER BY " + KEY_NAMEMENU + " DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ItemMenu itemMenu = new ItemMenu();
                itemMenu.setId(cursor.getInt(0));
                itemMenu.setName(cursor.getString(1));
                itemMenu.setPrice(cursor.getString(2));
                itemMenu.setDetails(cursor.getString(3));
                itemMenu.setImage(cursor.getString(4));
                itemMenuList.add(itemMenu);
            } while (cursor.moveToNext());
        }
        return itemMenuList;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPhone(cursor.getString(3));
                user.setGender(cursor.getString(4));
                user.setPassword(cursor.getString(5));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    /*public List<CategoryMenu> getAllCategory() {
        List<CategoryMenu> categoryMenuList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORY + " OREDER BY " +
                KEY_ID_CATEGORY + "DESC";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CategoryMenu categoryMenu = new CategoryMenu();
                categoryMenu.setId(cursor.getInt(0));
                categoryMenu.setName(cursor.getString(1));
                categoryMenu.setImage(cursor.getInt(2));
                categoryMenuList.add(categoryMenu);
            } while (cursor.moveToNext());
        }
        return categoryMenuList;
    }*/

    public void updateMenu(ItemMenu itemMenu) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMEMENU, itemMenu.getMenu());
        values.put(KEY_PRICEMENU, itemMenu.getPrice());
        values.put(KEY_IMAGEMENU, itemMenu.getImage());
        values.put(KEY_DETAILSMENU, itemMenu.getDetails());
        Log.d(TAG, "Update ItemMenu Succes!" + itemMenu.toString());

        db.update(TABLE_MENU, values, KEY_ID_MENU + " = '" + itemMenu.getId() + "'", null);
    }

    public void updateUser(User user) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAMEUSER, user.getName());
        values.put(KEY_EMAILUSER, user.getEmail());
        values.put(KEY_PHONEUSER, user.getPhone());
        values.put(KEY_GENDERUSER, user.getGender());
        values.put(KEY_PASSWORDUSER, user.getPassword());
        Log.d(TAG, "Update Succes !");

        db.update(TABLE_USER, values, KEY_ID_USER + " = '" + user.getId() + "'", null);
    }

   /* public void updateCategory(CategoryMenu categoryMenu) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMECATEGORY, categoryMenu.getName());
        values.put(KEY_ID_CATEGORY, categoryMenu.getImage());
        Log.d(TAG, "Update Category Succes!");

        db.update(TABLE_CATEGORY, values, KEY_ID_CATEGORY + " = '" + categoryMenu.getId() + "'", null);
    }*/

    public void deleteMenu(ItemMenu itemMenu) {
        db.delete(TABLE_MENU, KEY_ID_MENU + " = ?", new String[]{String.valueOf(itemMenu.getId())});
        Log.d(TAG, "Delete Succes!");
    }

    public void deleteMenuById(int id) {
        db.execSQL("DELETE FROM " + TABLE_MENU + " WHERE " + KEY_ID_MENU + " = " + id);
    }

    /*public void deleteCategory(CategoryMenu categoryMenu) {
        db.delete(TABLE_CATEGORY, KEY_ID_CATEGORY + " = ?",
                new String[]{String.valueOf(categoryMenu.getId())});
        Log.d(TAG, "Delete Succes");
    }*/

    public boolean checkUser(String email, String pass) {
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAILUSER + " = '" + email
                + "' AND " + KEY_PASSWORDUSER + " = '" + pass + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.moveToFirst();
    }
}
