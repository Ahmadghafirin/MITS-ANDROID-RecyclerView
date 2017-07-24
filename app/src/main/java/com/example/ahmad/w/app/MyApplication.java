package com.example.ahmad.w.app;

import android.app.Application;

import com.example.ahmad.w.SessionManager;
import com.example.ahmad.w.database.DataBaseHandler;

/**
 * Created by ahmad on 23/07/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
        DataBaseHandler.init(this);
    }
}
