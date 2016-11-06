package com.example.rohitmathew.bucket_it;


import android.app.Application;

import io.realm.Realm;

/**
 * Created by Eesh on 06/11/16.
 */

public class Singleton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }
}
