package com.qb.findwork.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qb.findwork.R;
import com.qb.findwork.util.HttpGetPerson;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

    // UI references.
    private EditText mPhoneView;
    private EditText mPasswordView;
    private TextView register;
    private ImageView back;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button mSignInButton;
    boolean isRemember, isLogin;
    private TextView tv_look;
    private String registerPhone;
    private ProgressDialog mPd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        init();


    }


    public void init() {
        mPd = new ProgressDialog(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        isRemember = pref.getBoolean("remember_password", false);
        isLogin = pref.getBoolean("islogin", false);
        mPhoneView = (EditText) findViewById(R.id.phone);
        mPasswordView = (EditText) findViewById(R.id.password);
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        back = (ImageView) findViewById(R.id.login_back);
        register = (TextView) findViewById(R.id.register);
        tv_look = (TextView) findViewById(R.id.tv_look);
        tv_look.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);
        back.setOnClickListener(this);
        register.setOnClickListener(this);


        if (isRemember) {
            String phone = pref.getString("phone", "");
            String password = pref.getString("password", "");
            mPhoneView.setText(phone);
            mPasswordView.setText(password);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_button:
                // login();
                String phone = mPhoneView.getText().toString();
                String password = mPasswordView.getText().toString();
                new LAsync().execute(phone, password);
                //logintest();
                break;
            case R.id.tv_look:
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            default:
                break;


        }
    }

    public void login() {
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {

            // 发送账号密码，服务器进行查询，返回值1、登陆成功，2、登陆失败（密码错误，无账号）
            //线程同步handle
            String address = HttpUtil.ipUrl + "Testt?username=" + phone + "&userpass=" + password;
            HttpURLConnection connection = HttpUtil.sedHttpRequest(address);

            String jsonData = HttpGetString.HttpgetString(connection);
            Log.i("jsonData", jsonData);
            if (jsonData.equals("OK"))

            {
                editor = pref.edit();
                editor.putBoolean("remember_password", true);
                editor.putString("phone", phone);
                editor.putString("password", password);
                editor.putBoolean("islogin", true);
                editor.commit();
                Log.i("islogin", pref.getBoolean("remember_password", false) + "");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void logintest() {
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {


            if (phone.equals("123") && password.equals("123"))

            {
                editor = pref.edit();
                editor.putBoolean("remember_password", true);
                editor.putString("phone", phone);
                editor.putString("password", password);
                editor.putBoolean("islogin", true);
                editor.commit();
                Log.i("islogin", pref.getBoolean("remember_password", false) + "");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //  HttpUtil.closeHttp();
    }

    private class LAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            mPd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            //传入手机号和密码
            String phone = params[0];
            String password = params[1];

            String address = HttpUtil.ipUrl + "Testt?username=" + phone + "&userpass=" + password;
            HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
            if (connection != null) {
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonData", jsonData);
                if (jsonData.equals("OK"))

                {
                    HttpGetPerson.getPerson(LoginActivity.this);
                    editor = pref.edit();
                    editor.putBoolean("remember_password", true);
                    editor.putString("phone", phone);
                    editor.putString("password", password);
                    editor.putBoolean("islogin", true);
                    editor.commit();
                    Log.i("islogin", pref.getBoolean("remember_password", false) + "");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return "ok";
                }

                return "no";
            }
            return "net";
        }

        @Override
        protected void onPostExecute(String s) {

            mPd.dismiss();
            if (s.equals("no")) {
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            } else if (s.equals("net")) {
                Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

