package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.NetIsWifi;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

    public void init() {
        back= (ImageView)findViewById(R.id.about_back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_back:
                finish();
                break;
            default:
                break;
        }
    }
}
