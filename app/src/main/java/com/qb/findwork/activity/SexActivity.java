package com.qb.findwork.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.ShareDate;

public class SexActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView sexMan,sexManNo,sexWomanNo,sexWoman;
    private  boolean sexIsMan=true;
    private ImageView sexBack;
    private FrameLayout frameLayouttMan;
    private FrameLayout frameLayoutWoamn;
    private TextView sex_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        init();
    }
    public  void  init(){
        sex_save= (TextView) findViewById(R.id.sex_save);
        sexMan= (ImageView) findViewById(R.id.sex_man);
        sexWoman= (ImageView) findViewById(R.id.sex_women);
        sexBack= (ImageView) findViewById(R.id.sex_back);
        sexManNo= (ImageView) findViewById(R.id.sex_man_no);
        sexWomanNo= (ImageView) findViewById(R.id.sex_women_no);
        frameLayouttMan= (FrameLayout) findViewById(R.id.fl_man);
        frameLayoutWoamn= (FrameLayout) findViewById(R.id.fl_woman);
        sexBack.setOnClickListener(this);
        sex_save.setOnClickListener(this);
        frameLayouttMan.setOnClickListener(this);
        frameLayoutWoamn.setOnClickListener(this);

        setSexpic();
    }


    public  void setSexpic(){
        String sex=ShareDate.getString("personSex",SexActivity.this);
        if(sex.equals("男")||sex.equals("")){
            sexIsMan=true;
            sexMan.setVisibility(View.VISIBLE);
            sexWoman.setVisibility(View.INVISIBLE);
        }
        else{
            sexIsMan=false;
            sexMan.setVisibility(View.INVISIBLE);
            sexWoman.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fl_man:
                if(sexIsMan==false)
                {
                    sexMan.setVisibility(View.VISIBLE);
                    sexWoman.setVisibility(View.INVISIBLE);
                    sexIsMan=true;

                }
                break;
            case R.id.fl_woman:
                if(sexIsMan==true)
                {
                    sexMan.setVisibility(View.INVISIBLE);
                    sexWoman.setVisibility(View.VISIBLE);
                    sexIsMan=false;

                }
                break;
            case R.id.sex_back:
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            case  R.id.sex_save:
                if(sexIsMan==true){
                    ShareDate.setString("personSex","男",SexActivity.this);
                }
                else{
                    ShareDate.setString("personSex","女",SexActivity.this);
                }
                finish();
                overridePendingTransition(R.anim.base_slide_remain, R.anim.base_slide_right_out);
                break;
            default:break;
        }
    }
}
