package com.qb.findwork.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qb.findwork.MyApplication;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ShareDate {

    private static SharedPreferences pref;

    private static SharedPreferences.Editor editor;

    public static void setString(String key, String value, Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static void setBoolean(String key, boolean value, Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key) {

        return pref.getBoolean(key, false);
    }

    public static String getString(String key, Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(key, "");
    }
}
