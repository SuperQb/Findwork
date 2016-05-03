package com.qb.findwork.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.qb.findwork.MyApplication;
import com.qb.findwork.R;
import com.qb.findwork.activity.MainActivity;

import java.util.Locale;


public class ChangeLanguage {

    public static String LANGUAGE="language";
    public static String ENGLISH="English";
    public static String CHINESE="Chinese";

    public  static  void changeEn(Context context){
//        Resources resource = context.getResources();
//        Configuration config = resource.getConfiguration();
//        config.locale = Locale.ENGLISH;
//        //config.locale = Locale.CHINESE;
//        context.getResources().updateConfiguration(config, null);
        ShareDate.setString(LANGUAGE, ENGLISH, context);

    }
    public  static  void changeZh(Context context){

//        Resources resource = context.getResources();
//        Configuration config = resource.getConfiguration();
//        config.locale = Locale.CHINESE;
//        context.getResources().updateConfiguration(config, null);
        ShareDate.setString(LANGUAGE, CHINESE, context);

    }
    public static void  getLanguage(Context context){
        String lan=ShareDate.getString(LANGUAGE,context);
        Resources resource = context.getResources();
        Configuration config = resource.getConfiguration();
        if(lan.equals(ENGLISH))
        {
            config.locale = Locale.ENGLISH;

        }
        else {
            config.locale = Locale.CHINESE;
        }
        context.getResources().updateConfiguration(config, null);

    }
}
