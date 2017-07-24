package com.example.ahmad.w;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmad on 23/07/17.
 */

public class SessionManager {

    private static final String AUTH_PREFERENCES = "auth_preferences";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String GENDER = "gender";
    private static final String PASSWORD = "password";
    public static final String IS_LOGGEDIN = "is_loggedin";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SessionManager instance;

    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        instance = new SessionManager(context);
    }

    public synchronized static SessionManager getInstance() {
        return instance;
    }

    public void setLogin(String email, String pass) {
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, pass);
        editor.putBoolean(IS_LOGGEDIN, true);
        editor.commit();
    }

    public String getEmail() {
        String email = sharedPreferences.getString(EMAIL, "");
        return email;
    }

    public String getPassword() {
        String pass = sharedPreferences.getString(PASSWORD, "");
        return pass;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGEDIN, false);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
