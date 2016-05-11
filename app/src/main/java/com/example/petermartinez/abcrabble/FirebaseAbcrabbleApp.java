package com.example.petermartinez.abcrabble;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by petermartinez on 5/3/16.
 */
public class FirebaseAbcrabbleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
