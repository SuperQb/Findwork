package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;

public class CompileWorkActivity extends AppCompatActivity implements View.OnClickListener {




    private EditText workPosition;
    private EditText workPay;
    private EditText workSex;
    private EditText workLocation;
    private EditText workIntroduce;
    private EditText workContent;
    private EditText workRequired;
    private EditText workPhone;

    private ImageView workBack;
    private TextView workpush;
    private String position,pay,sex,location,introduce,content,required,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_work);
        init();
    }
    public void init(){
        workpush= (TextView) findViewById(R.id.work_push);
        workBack= (ImageView) findViewById(R.id.compile_work_back);
        workPosition= (EditText) findViewById(R.id.compile_work_position);
        workPay= (EditText) findViewById(R.id.compile_work_pay);
        workSex= (EditText) findViewById(R.id.compile_work_sex);
        workLocation= (EditText) findViewById(R.id.compile_work_location);
        workIntroduce= (EditText) findViewById(R.id.compile_tv_work_more_introduce);
        workContent= (EditText) findViewById(R.id.compile_tv_work_more_content);
        workRequired= (EditText) findViewById(R.id.compile_tv_work_more_required);
        workPhone= (EditText) findViewById(R.id.compile_work_phone);

        workBack.setOnClickListener(this);
        workpush.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.compile_work_back:
                finish();
                break;
            case R.id.work_push:
                push();
            default:break;
        }
    }
    public void push(){

        idAllIn();
        Log.i("workPosition", workPosition.getText().toString());
        Log.i("workPay", workPay.getText().toString());
        Log.i("workSex", workSex.getText().toString());
        Log.i("workIntroduce", workIntroduce.getText().toString());
        Log.i("workContent", workContent.getText().toString());
        Log.i("workLocation", workLocation.getText().toString());
        Log.i("workRequired", workRequired.getText().toString());
        Log.i("workPhone", workPhone.getText().toString());
    }
    public boolean idAllIn(){
        getText();

        if(position.isEmpty()||pay.isEmpty()||sex.isEmpty()||location.isEmpty()||position.isEmpty()||content.isEmpty()||required.isEmpty()||phone.isEmpty()){
            Log.i("all","请填写完整");
            return false;

        }
        return true;


    }
    public void getText(){
        position=workPosition.getText().toString();
        pay=workPay.getText().toString();
        sex=workSex.getText().toString();
        location=workLocation.getText().toString();
        introduce=workIntroduce.getText().toString();
        content=workContent.getText().toString();
        required=workRequired.getText().toString();
        phone=workPhone.getText().toString();
    }
}
