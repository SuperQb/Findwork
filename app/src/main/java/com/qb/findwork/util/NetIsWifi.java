package com.qb.findwork.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qb.findwork.MyApplication;


public class NetIsWifi {

    public static String NETTYPE = "nettype";
    public static String NETWIFI = "netwifi";
    public static String NETALL = "netall";
    public static boolean iswifi = true;

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWiFiNetworkInfo != null) {
            iswifi = mWiFiNetworkInfo.isAvailable();
            return iswifi;
        }
        iswifi = false;
        return iswifi;
    }
}
