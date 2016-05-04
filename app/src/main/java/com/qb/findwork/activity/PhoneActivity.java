package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.ShareDate;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView phone_back;
    private EditText et_phone;
    private TextView phone_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        init();
    }

    public void init() {

        et_phone = (EditText) findViewById(R.id.activity_per_phone);
        phone_back = (ImageView) findViewById(R.id.phone_back);
        phone_save = (TextView) findViewById(R.id.phone_save);
        phone_back.setOnClickListener(this);
        phone_save.setOnClickListener(this);
        String phone = ShareDate.getString("personPhone", PhoneActivity.this);
        et_phone.setText(phone);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            case R.id.phone_save:
                String phone = et_phone.getText().toString();
                ShareDate.setString("personPhone", phone, PhoneActivity.this);
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            default:
                break;

        }
    }
}
