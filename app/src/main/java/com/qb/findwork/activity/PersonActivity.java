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


    RelativeLayout Relat_name,Relat_sex,Relat_age,Relat_phone;
    ImageView per_photo;
    TextView per_name,per_sex,per_age,per_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }
    public void init (){
        Relat_name= (RelativeLayout) findViewById(R.id.Relat_name);
        Relat_sex= (RelativeLayout) findViewById(R.id.Relat_sex);
        Relat_age= (RelativeLayout) findViewById(R.id.Relat_age);
        Relat_phone= (RelativeLayout) findViewById(R.id.Relat_phone);
        per_photo= (ImageView) findViewById(R.id.per_photo);
        per_name= (TextView) findViewById(R.id.per_name);
        per_sex= (TextView) findViewById(R.id.per_sex);
        per_age= (TextView) findViewById(R.id.per_age);
        per_phone= (TextView) findViewById(R.id.per_phone);

    }

    @Override
    public void onClick(View v) {

    }
}
