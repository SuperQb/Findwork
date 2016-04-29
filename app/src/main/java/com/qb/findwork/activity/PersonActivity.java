package com.qb.findwork.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.DataClearManager;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;
import com.qb.findwork.util.ShareDate;
import com.qb.findwork.view.WheelView;

import java.net.HttpURLConnection;
import java.util.Arrays;

import me.drakeet.materialdialog.MaterialDialog;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PLANETS = new String[]{"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};
    private RelativeLayout Relat_name, Relat_sex, Relat_age, Relat_phone;
    private ImageView per_photo;
    private TextView per_name, per_sex, per_age, per_phone;
    private ImageView back;


    private SharedPreferences pref;
    private String name, age, sex, phone;
    private TextView perSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }


    public void init() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
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
        perSave = (TextView) findViewById(R.id.person_save);
        Relat_name.setOnClickListener(this);
        Relat_phone.setOnClickListener(this);
        Relat_sex.setOnClickListener(this);
        Relat_age.setOnClickListener(this);
        back.setOnClickListener(this);
        perSave.setOnClickListener(this);
        setPerson();
    }

    public void setPerson() {
        name = pref.getString("personName", "兼职校园");
        age = pref.getString("personAge", "18");
        sex = pref.getString("personSex", "男");
        phone = pref.getString("personPhone", "10086");
        per_name.setText(name);
        per_age.setText(age);
        per_sex.setText(sex);
        per_phone.setText(phone);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.per_back:
                finish();
                break;
            case R.id.Relat_name:
                Intent intentName = new Intent(PersonActivity.this, NameActivity.class);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                startActivity(intentName);
                break;
            case R.id.Relat_phone:
                Intent intentPhone = new Intent(PersonActivity.this, PhoneActivity.class);
                startActivity(intentPhone);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_sex:
                Intent intentSex = new Intent(PersonActivity.this, SexActivity.class);
                startActivity(intentSex);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_age:

                View outerView = LayoutInflater.from(this).inflate(R.layout.activity_age, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_wv);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(20);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        age = item;
                    }
                });
                final MaterialDialog mMaterialDialog = new MaterialDialog(this);
                mMaterialDialog.setTitle("年龄");
                mMaterialDialog.setView(outerView);
                mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        Log.i("test", age);

                    }
                });
                mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();

                    }
                });

                mMaterialDialog.show();
                break;
            case R.id.person_save:
                sendPerson();
            default:
                break;

        }
    }

    public void sendPerson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String rephone = ShareDate.getString("phone");
                String address = HttpUtil.ipUrl + "Testt/?name=" + name
                        + "&age=" + age
                        + "&sex=" + sex
                        + "phone" + phone
                        + "rephone" + rephone;
                HttpUtil.sedHttpRequest(address);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.closeHttp();
    }
}
