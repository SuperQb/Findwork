package com.qb.findwork.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qb.findwork.R;
import com.qb.findwork.util.ActivityManagers;
import com.qb.findwork.util.ChangeLanguage;
import com.qb.findwork.util.ShareDate;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout rl_zh,rl_en;
    private ImageView iv_zh,iv_en;
    private ImageView settting_lan_back;
    private   String lanSave = null;
    private Button bt_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        init();


    }
    public void init(){
        rl_en= (RelativeLayout) findViewById(R.id.Relat_setting_zh);
        rl_zh= (RelativeLayout) findViewById(R.id.Relat_setting_en);
        bt_save= (Button) findViewById(R.id.language_save);
        settting_lan_back= (ImageView) findViewById(R.id.settting_lan_back);
        iv_zh= (ImageView) findViewById(R.id.select_zh);
        iv_en= (ImageView) findViewById(R.id.select_en);
        settting_lan_back.setOnClickListener(this);
        bt_save.setOnClickListener(this);
        rl_zh.setOnClickListener(this);
        rl_en.setOnClickListener(this);

        String lan=ShareDate.getString(ChangeLanguage.LANGUAGE,LanguageActivity.this);
        if(lan.equals(ChangeLanguage.CHINESE))
        {
            iv_zh.setVisibility(View.VISIBLE);
            iv_en.setVisibility(View.INVISIBLE);
        }
        else{
            iv_zh.setVisibility(View.INVISIBLE);
            iv_en.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Relat_setting_zh:
                lanSave=ChangeLanguage.CHINESE;
                iv_zh.setVisibility(View.VISIBLE);
                iv_en.setVisibility(View.INVISIBLE);
                break;
            case R.id.Relat_setting_en:
                lanSave=ChangeLanguage.ENGLISH;
                iv_zh.setVisibility(View.INVISIBLE);
                iv_en.setVisibility(View.VISIBLE);
                break;
            case R.id.language_save:

                if(lanSave.equals(ChangeLanguage.CHINESE))
                {
                    ChangeLanguage.changeZh(LanguageActivity.this);
                }
                else if(lanSave.equals(ChangeLanguage.ENGLISH))
                {
                    ChangeLanguage.changeEn(LanguageActivity.this);
                }
                ActivityManagers.finishAll();
                Intent it = new Intent(LanguageActivity.this, MainActivity.class);
                startActivity(it);
                finish();

                break;
            case R.id.settting_lan_back:
                finish();
                break;
        }
    }

    private void restart() {
        ActivityManagers.finishAll();
        Intent it = new Intent(LanguageActivity.this, MainActivity.class);
        startActivity(it);
    }

}
