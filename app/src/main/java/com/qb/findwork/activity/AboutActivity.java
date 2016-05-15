package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qb.findwork.R;
import com.qb.findwork.util.NetIsWifi;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView about_back;
    private Button bt_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

    public void init() {
        about_back = (ImageView) findViewById(R.id.about_back);
        bt_net= (Button) findViewById(R.id.button);
        bt_net.setOnClickListener(this);
        about_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_back:
                finish();
                break;
//            case R.id.button:
//                if(NetIsWifi.isWifiConnected(AboutActivity.this)){
//                    Log.i("test","netiswifi");
//                }else{
//                    Log.i("test","net is not wifi");
//                }
//                break;
            default:
                break;
        }
    }
}
