package com.qb.findwork.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.MyApplication;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.Person;

import java.net.HttpURLConnection;
import java.util.List;

public class HttpGetPerson {
    private static SharedPreferences pref;
    public static void getPerson(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        new Thread(new Runnable() {
            @Override
            public void run() {

                //向服务器发送注册时候的手机号，服务器进行查询，返回个人详细资料

                String phone = pref.getString("phone", "NO");
                String address = HttpUtil.ipUrl+"Getperson?registerPhone="+phone;
                HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonData", jsonData);
                parseJSONWithJSONObject(jsonData);
            }
        }).start();
    }
    //解析json，存入本地
    private static void parseJSONWithJSONObject(String jsonData) {
        SharedPreferences.Editor editor;
        Gson gson = new Gson();
        ListData.personList = gson.fromJson(jsonData, new TypeToken<List<Person>>() {
        }.getType());
        for (Person person : ListData.personList) {
            editor = pref.edit();
            editor.putString("personName", person.getName());
            editor.putString("personSex",  person.getSex());
            editor.putString("personAge", person.getAge());
            editor.putString("personPhone", person.getPhone());
            editor.putString("personIcon",person.getIcon());
            editor.commit();

        }

    }


}
