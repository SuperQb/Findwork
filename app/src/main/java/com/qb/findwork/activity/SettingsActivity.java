package com.qb.findwork.activity;


import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.qb.findwork.R;
import com.qb.findwork.util.ActivityManagers;
import com.qb.findwork.util.ChangeLanguage;
import com.qb.findwork.util.DataClearManager;
import com.qb.findwork.util.ShareDate;

import me.drakeet.materialdialog.MaterialDialog;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView setting_back;
    private RelativeLayout Relat_setting_cash, Relat_setting_language,Relat_setting_net;
    private TextView setting_cash_m;
    private Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();

    }

    public void init() {
        ActivityManagers.addActivity(this);
        logout_button = (Button) findViewById(R.id.logout_button);
        setting_back = (ImageView) findViewById(R.id.settting_back);
        Relat_setting_cash = (RelativeLayout) findViewById(R.id.Relat_setting_cash);
        setting_cash_m = (TextView) findViewById(R.id.setting_cash_m);
        Relat_setting_language = (RelativeLayout) findViewById(R.id.Relat_setting_language);
        Relat_setting_net= (RelativeLayout) findViewById(R.id.Relat_setting_net);
        updateCacheView();
        setting_back.setOnClickListener(this);
        logout_button.setOnClickListener(this);
        Relat_setting_cash.setOnClickListener(this);
        Relat_setting_language.setOnClickListener(this);
        Relat_setting_net.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.settting_back:
                finish();
                break;
            case R.id.Relat_setting_cash:
                final MaterialDialog mMaterialDialog = new MaterialDialog(this);
                mMaterialDialog.setTitle("提示");
                mMaterialDialog.setMessage("是否清楚缓存");
                mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new Thread(new Runnable() {
                            public void run() {
                                DataClearManager.cleanApplicationData(SettingsActivity.this);
                                SettingsActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        mMaterialDialog.dismiss();
                                        updateCacheView();
                                    }
                                });
                            }
                        }).start();

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


            case R.id.Relat_setting_net:

                Intent intentNet=new Intent(this,NetActivity.class);
                startActivity(intentNet);
                break;

            case R.id.Relat_setting_language:
                Intent intentlan = new Intent(this, LanguageActivity.class);
                startActivity(intentlan);

                break;
            case R.id.logout_button:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                ActivityManagers.finishAll();
                ShareDate.setBoolean("islogin", false, SettingsActivity.this);
                break;
            default:
                break;

        }


    }

    private void updateCacheView() {


        String size = DataClearManager.getApplicationDataSize(getApplication());
        if (size != null) {
            setting_cash_m.setText(String.format("%s M", size));
        } else {
            setting_cash_m.setText("计算中...");
        }


    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityManagers.removeActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ChangeLanguage.getLanguage(SettingsActivity.this);
    }
}
