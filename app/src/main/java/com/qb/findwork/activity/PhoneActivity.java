package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView phone_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        init();
    }

    public void init() {

        phone_back= (ImageView) findViewById(R.id.phone_back);
        phone_back.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            default:
                break;

        }
    }
}
