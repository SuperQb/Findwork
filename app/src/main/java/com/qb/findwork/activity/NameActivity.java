package com.qb.findwork.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.ShareDate;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView name_back;
    private EditText tv_name;
    private  TextView name_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        init();

    }
    public void init(){
        name_save= (TextView) findViewById(R.id.name_save);
        tv_name= (EditText) findViewById(R.id.activity_per_name);
        name_back= (ImageView) findViewById(R.id.name_back);
        name_back.setOnClickListener(this);
        String name=ShareDate.getString("personName",NameActivity.this);
        tv_name.setText(name);
        name_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            case R.id.name_save:
                String name =tv_name.getText().toString();
                ShareDate.setString("personName",name,NameActivity.this);
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;

            default:
                break;

        }
    }
}
