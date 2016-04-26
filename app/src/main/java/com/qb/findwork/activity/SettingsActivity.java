package com.qb.findwork.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.qb.findwork.R;
import com.qb.findwork.util.DataClearManager;

import me.drakeet.materialdialog.MaterialDialog;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView setting_back;
    private RelativeLayout Relat_setting_cash;
    private TextView setting_cash_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();

    }

    public void init() {
        setting_back = (ImageView) findViewById(R.id.settting_back);
        Relat_setting_cash = (RelativeLayout) findViewById(R.id.Relat_setting_cash);
        setting_cash_m = (TextView) findViewById(R.id.setting_cash_m);
        updateCacheView();
        setting_back.setOnClickListener(this);
        Relat_setting_cash.setOnClickListener(this);

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
                        mMaterialDialog.dismiss();
                        DataClearManager.cleanApplicationData(SettingsActivity.this);
                        updateCacheView();

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

            default:
                break;

        }


    }

    private void updateCacheView() {
        setting_cash_m.setText(String.format("%s M", DataClearManager.getApplicationDataSize(getApplication())));
    }
}
