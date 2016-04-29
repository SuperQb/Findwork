package com.qb.findwork.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qb.findwork.MyApplication;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ShareDate {

    private static SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());

    private static SharedPreferences.Editor editor = pref.edit();

    public static void setString(String key, String value) {

        editor.putString(key, value);
        editor.commit();

    }
    public static void setBoolean(String key, boolean value) {

        editor.putBoolean(key, value);
        editor.commit();
    }

    public static  boolean  getBoolean(String key) {

        return pref.getBoolean(key, false);
    }
    public static  String  getString(String key) {

        return pref.getString(key, "");
    }
}
