package com.qb.findwork.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.R;
import com.qb.findwork.data.UserLogin;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView register_back;
    private Button button_register;
    private EditText activity_register_phone;
    private EditText activity_register_password;
    private EditText activity_register_password_two;
    private EditText activity_per_code;
    private String username, userpass, userpassTwo, code;
    private Button get_code;
    private final String appKEY = "1205e0cc1f874";
    private final String appSecret = "6031c73fbee92a4a53f6a3c3def59792";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


    }

    public void init() {
        SMSSDK.initSDK(this, appKEY, appSecret);
        register_back = (ImageView) findViewById(R.id.register_back);
        button_register = (Button) findViewById(R.id.button_register);
        activity_register_phone = (EditText) findViewById(R.id.activity_register_phone);
        activity_register_password = (EditText) findViewById(R.id.activity_register_password);
        activity_register_password_two = (EditText) findViewById(R.id.activity_register_password_two);
        activity_per_code = (EditText) findViewById(R.id.activity_per_code);
        get_code = (Button) findViewById(R.id.get_code);
        register_back.setOnClickListener(this);
        button_register.setOnClickListener(this);
        get_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.get_code:
                getCode();
                break;
            case R.id.button_register:
                register();
                break;
            //isAllIn();
            default:
                break;
        }
    }

    public void register() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //try {
                userpass = activity_register_password.getText().toString();
                username = activity_register_phone.getText().toString();

                String address = HttpUtil.ipUrl+"Testt";

                HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
                //发送数据

                //接收数据（是否注册成功，检查重复）
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonData", jsonData);
                //parseJSONWithJSONObject(jsonData);
            }
        }).start();
    }

    public boolean isAllIn() {
        getText();

        if (username.isEmpty()) {
            Log.i("allin", "请填写电话");
            return false;
        } else if (code.isEmpty())

        {
            Log.i("allin", "请填写验证码");
            return false;
        } else if (userpass.isEmpty() || userpassTwo.isEmpty()) {
            Log.i("allin", "请填写密码");
            return false;
        } else if (!userpass.equals(userpassTwo)) {
            Log.i("allin", "请保持填写密码相同");
            return false;
        }


        return true;
    }

    public void getText() {
        userpass = activity_register_password.getText().toString();
        username = activity_register_phone.getText().toString();
        userpassTwo = activity_register_password_two.getText().toString();
        code = activity_per_code.getText().toString();
    }

    public void parseJSONWithJSONObject(String jsonData) {


        Gson gson = new Gson();
        List<UserLogin> userList = gson.fromJson(jsonData, new TypeToken<List<UserLogin>>() {
        }.getType());
        for (UserLogin user : userList) {
            Log.i("useid", user.getUserId());
            Log.i("username", user.getUsername());
        }

    }

    public void getCode() {
        initSDK();
        //SMSSDK.initSDK(this, appKEY, appSecret);
        SMSSDK.getVerificationCode("86", "15202902579");

    }

    /**
     * 初始化短信SDK
     */
    private void initSDK() {

        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                //requestCodeBtn.setText("重新发送(" + i-- + ")");
            } else if (msg.what == -8) {
                //requestCodeBtn.setText("获取验证码");
                //requestCodeBtn.setClickable(true);
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.i("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();

                        //数据发送发送服务器，进行注册
                        register();

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
