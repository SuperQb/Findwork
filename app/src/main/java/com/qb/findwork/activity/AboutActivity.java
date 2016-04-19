package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView about_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

    public void init() {
        about_back = (ImageView) findViewById(R.id.about_back);
        about_back.setOnClickListener(this);
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
