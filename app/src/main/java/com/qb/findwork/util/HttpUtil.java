package com.qb.findwork.util;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by QB on 2016/4/23.
 */
public class HttpUtil {

    public static String ipUrl="http://192.168.0.4:8080/Test/";
    public static HttpURLConnection connection ;

    public  static HttpURLConnection sedHttpRequest(String address){
        try {
            URL url=new URL(address);
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(8000);
//            connection.setReadTimeout(8000);
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
            connection.connect();
            if(connection.getResponseCode()==200){

                Log.i("test","网络连接成功");
                return connection;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeHttp(){
        connection.disconnect();
    }

}
