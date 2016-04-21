package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qb.findwork.R;

public class CompileEmployActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView employBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_employ);
        init();
    }
    public void init(){
        employBack= (ImageView) findViewById(R.id.compile_employ_back);
        employBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.compile_employ_back:
                finish();
                break;
            default:break;
        }
    }
}
