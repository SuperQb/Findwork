package com.qb.findwork.util;

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

    public  static  void changeEn(){
        Resources resource = MyApplication.getContext().getResources();
        Configuration config = resource.getConfiguration();
        config.locale = Locale.ENGLISH;
        //config.locale = Locale.CHINESE;
        MyApplication.getContext().getResources().updateConfiguration(config, null);
        ShareDate.setString(LANGUAGE,ENGLISH);

    }
    public  static  void changeZh(){

        Resources resource = MyApplication.getContext().getResources();
        Configuration config = resource.getConfiguration();
        config.locale = Locale.CHINESE;
        MyApplication.getContext().getResources().updateConfiguration(config, null);
        ShareDate.setString(LANGUAGE, CHINESE);


    }
}
