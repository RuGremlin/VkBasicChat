package com.dbronnikov.vkchat.app.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by dbronnikov on 11/02/15.
 * Copyright (c) 2014 Dmitriy Bronnikov. All rights reserved.
 */
public class Session {
    public String access_token;
    public long user_id;

    public void save(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("access_token", access_token);
        editor.putLong("user_id", user_id);
        editor.commit();
    }

    public void restore(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        access_token=prefs.getString("access_token", null);
        user_id=prefs.getLong("user_id", 0);
    }
}
