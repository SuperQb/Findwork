package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView setting_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }
    public void init(){
        setting_back= (ImageView) findViewById(R.id.settting_back);
        setting_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settting_back:
                finish();
                break;
            default:break;
        }
    }
}
