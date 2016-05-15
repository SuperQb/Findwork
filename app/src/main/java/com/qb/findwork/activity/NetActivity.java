package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qb.findwork.R;
import com.qb.findwork.util.NetIsWifi;
import com.qb.findwork.util.ShareDate;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mAll;
    private RelativeLayout mWifi;
    private ImageView mNetAll;
    private ImageView mNetWifi;
    private ImageView mback;
    private Button mNetSave;
    private String mNetString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        init();
    }

    private void init() {
        mAll= (RelativeLayout) findViewById(R.id.Relat_net_all);
        mWifi= (RelativeLayout) findViewById(R.id.Relat_net_wifi);
        mNetAll = (ImageView) findViewById(R.id.select_all);
        mNetWifi = (ImageView) findViewById(R.id.select_wifi);
        mNetSave = (Button) findViewById(R.id.net_save);
        mback= (ImageView) findViewById(R.id.net_back);
        mAll.setOnClickListener(this);
        mWifi.setOnClickListener(this);
        mNetSave.setOnClickListener(this);
        mback.setOnClickListener(this);
        mNetString= ShareDate.getString(NetIsWifi.NETTYPE,NetActivity.this);
        if(mNetString.equals(NetIsWifi.NETWIFI)){
            mNetAll.setVisibility(View.INVISIBLE);
            mNetWifi.setVisibility(View.VISIBLE);
        }
        else{
            mNetAll.setVisibility(View.VISIBLE);
            mNetWifi.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Relat_net_all:
                mNetAll.setVisibility(View.VISIBLE);
                mNetWifi.setVisibility(View.INVISIBLE);
                mNetString=NetIsWifi.NETALL;
                break;
            case R.id.Relat_net_wifi:
                mNetAll.setVisibility(View.INVISIBLE);
                mNetWifi.setVisibility(View.VISIBLE);
                mNetString=NetIsWifi.NETWIFI;
                break;
            case R.id.net_save:
                ShareDate.setString(NetIsWifi.NETTYPE,mNetString,NetActivity.this);
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            case R.id.net_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            default:break;
        }
    }
}
