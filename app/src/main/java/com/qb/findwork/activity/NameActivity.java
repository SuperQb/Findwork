package com.qb.findwork.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView name_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        init();

    }
    public void init(){


        name_back= (ImageView) findViewById(R.id.name_back);
        name_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
//            case R.id.Relat_name:
//                保存
//               Intent intent = new Intent(PersonActivity.this, NameActivity.class);
//                startActivity(intent);
//                break;

            default:
                break;

        }
    }
}
