package com.qb.findwork.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by QB on 2016/4/23.
 */
public class HttpUtil {

    public  static HttpURLConnection sedHttpRequest(String address){
        HttpURLConnection connection ;
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

                return connection;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
