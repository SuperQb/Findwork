package com.qb.findwork.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qb.findwork.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer time = new Timer();
        final Intent intent = new Intent(this, MainActivity.class);
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
