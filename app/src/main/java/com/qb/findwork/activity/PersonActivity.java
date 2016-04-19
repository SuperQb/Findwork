package com.qb.findwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qb.findwork.R;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout Relat_name, Relat_sex, Relat_age, Relat_phone;
    private ImageView per_photo;
    private TextView per_name, per_sex, per_age, per_phone;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }

    public void init() {
        Relat_name = (RelativeLayout) findViewById(R.id.Relat_name);
        Relat_sex = (RelativeLayout) findViewById(R.id.Relat_sex);
        Relat_age = (RelativeLayout) findViewById(R.id.Relat_age);
        Relat_phone = (RelativeLayout) findViewById(R.id.Relat_phone);
        per_photo = (ImageView) findViewById(R.id.per_photo);
        per_name = (TextView) findViewById(R.id.per_name);
        per_sex = (TextView) findViewById(R.id.per_sex);
        per_age = (TextView) findViewById(R.id.per_age);
        per_phone = (TextView) findViewById(R.id.per_phone);
        back = (ImageView) findViewById(R.id.per_back);
        Relat_name.setOnClickListener(this);
        Relat_phone.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.per_back:
                finish();
                break;
            case R.id.Relat_name:
                Intent intentName = new Intent(PersonActivity.this, NameActivity.class);
                startActivity(intentName);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_phone:
                Intent intentPhone = new Intent(PersonActivity.this, PhoneActivity.class);
                startActivity(intentPhone);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            default:
                break;

        }
    }
}
