package com.qb.findwork.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by QB on 2016/4/23.
 */
public class HttpGetString {
    public static String HttpgetString(HttpURLConnection connection) {
        try {
            String encoding = "UTF-8";
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,encoding));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
