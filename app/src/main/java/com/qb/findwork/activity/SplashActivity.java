package com.qb.findwork.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.R;
import com.qb.findwork.data.Person;
import com.qb.findwork.data.Work;
import com.qb.findwork.util.ChangeLanguage;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpGetWork;
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {
    private Intent intent;
    private SharedPreferences pref;
    private boolean idLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        idLogin = pref.getBoolean("remember_password", false);
        Timer time = new Timer();

        if (idLogin == true) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        ChangeLanguage.getLanguage(SplashActivity.this);
        //HttpGetWork.getWork();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                startActivity(intent);

                SplashActivity.this.finish();// 将本个activity结束，此句可有可无，看实际情况。
            }
        };
        time.schedule(task, 2000);// 2秒进行跳转

    }




}
