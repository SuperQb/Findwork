package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class CompileWorkActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView workBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_work);
        init();
    }
    public void init(){
        workBack= (ImageView) findViewById(R.id.compile_work_back);
        workBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.compile_work_back:
                finish();
                break;
            default:break;
        }
    }
}
