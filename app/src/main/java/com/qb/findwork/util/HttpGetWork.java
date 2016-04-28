package com.qb.findwork.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.data.Work;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by qubo on 2016/4/28.
 */
public class HttpGetWork {
    public static List<Work> workList;

    public static void getPerson() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //请求work数据
                String address = HttpUtil.ipUrl + "Testt2";
                HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonData", jsonData);
                parseJSONWithJSONObject(jsonData);
            }
        }).start();


    }

    public static void parseJSONWithJSONObject(String jsonData) {

        Gson gson = new Gson();
        workList = gson.fromJson(jsonData, new TypeToken<List<Work>>() {
        }.getType());
        for (Work work : workList) {
            //存入数据库

        }

    }
}
