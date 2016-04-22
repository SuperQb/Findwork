package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.data.Emloydate;

public class CompileEmployActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView employBack;
    private TextView employPUsh;
    private EditText employPosition;
    private EditText employPay;
    private EditText employSex;
    private EditText employLocation;
    private EditText employIntroduce;
    private EditText employContent;
    private EditText employRequired;
    private EditText employPhone;
    private Emloydate emloyDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_employ);
        init();
    }

    public void init() {

        employPosition = (EditText) findViewById(R.id.compile_employ_position);
        employPay = (EditText) findViewById(R.id.compile_employ_pay);
        employSex = (EditText) findViewById(R.id.compile_employ_sex);
        employLocation = (EditText) findViewById(R.id.compile_employ_location);
        employIntroduce = (EditText) findViewById(R.id.compile_tv_employ_more_introduce);
        employContent = (EditText) findViewById(R.id.compile_tv_employ_more_content);
        employRequired = (EditText) findViewById(R.id.compile_tv_employ_more_required);
        employPhone = (EditText) findViewById(R.id.compile_employ_phone);

        employBack = (ImageView) findViewById(R.id.compile_employ_back);
        employPUsh = (TextView) findViewById(R.id.compile_employ_push);


        employPUsh.setOnClickListener(this);
        employBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compile_employ_back:
                finish();
                break;
            case R.id.compile_employ_push:
                push();
                break;
            default:
                break;
        }
    }

    public void push() {

        //emloyDate=new Emloydate();
        Log.i("employPosition", employPosition.getText().toString());
        Log.i("employPay", employPay.getText().toString());
        Log.i("employSex", employSex.getText().toString());
        Log.i("employIntroduce", employIntroduce.getText().toString());
        Log.i("employContent", employContent.getText().toString());
        Log.i("employLocation", employLocation.getText().toString());
        Log.i("employRequired", employRequired.getText().toString());
        Log.i("employPhone", employPhone.getText().toString());


    }
}
