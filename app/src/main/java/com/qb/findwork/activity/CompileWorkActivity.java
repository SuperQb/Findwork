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
/*
    基于android平台下的兼职app。
        项目分为服务器和客户端两个部分。
            服务器只要负责数据库的增删改查和传输数据，此项目侧重点在android服务端，服务器端不做过多解释。
         android端主要分四大模块：兼职模块、个人资料模块、账号登陆注册模块、本地设置模块。
         兼职模块：可以查看所有兼职信息，并且可以分类查看工作招聘和个人求职。在查看详细页面中，通过按键给发送人打电话
         个人资料模块：分为头像、昵称、性别、电话。并且可以设置，从而与服务器同步。在头像设置中可以调用系统相机拍照或系统相册选择图片，并且可以进行裁剪。
         账号登陆注册模块：短信验证码注册，本地保存密码
         本地设置模块：网络设置（非wifi环境下不下载图片、查看缓存并清除缓存、app英文与中文的转换


 */
