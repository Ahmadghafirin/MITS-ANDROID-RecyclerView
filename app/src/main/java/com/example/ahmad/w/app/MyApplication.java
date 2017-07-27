package com.example.ahmad.w.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.ahmad.w.SessionManager;
import com.example.ahmad.w.model.ItemMenu;
import com.example.ahmad.w.model.User;


/**
 * Created by ahmad on 23/07/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SessionManager.init(this);

        Configuration.Builder builder = new Configuration.Builder(this);
        builder.addModelClasses(ItemMenu.class, User.class);
        ActiveAndroid.initialize(this);
    }
}
