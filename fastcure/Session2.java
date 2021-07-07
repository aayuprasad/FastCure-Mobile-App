package com.example.fastcure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session2 {
    private SharedPreferences prefs;
    public Session2(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }
    public void setid(String id) {
        prefs.edit().putString("id",id).commit();
    }
    public String getid() {
        String id = prefs.getString("id","");
        return id;
    }
}