package com.qb.findwork.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qb.findwork.MyApplication;


public class NetIsWifi {

    public  static boolean iswifi=true;

    public static boolean isWifiConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWiFiNetworkInfo != null) {
            iswifi=mWiFiNetworkInfo.isAvailable();
            return iswifi;
        }
        iswifi=false;
        return iswifi;
}
}
