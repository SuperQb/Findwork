package com.qb.findwork.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qb.findwork.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {

    // UI references.
    private EditText mPhoneView;
    private EditText mPasswordView;
    private TextView register;
    private ImageView back;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button mSignInButton;
    boolean isRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        init();


    }


    public void init() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        isRemember = pref.getBoolean("remember_password", false);
        mPhoneView = (EditText) findViewById(R.id.phone);
        mPasswordView = (EditText) findViewById(R.id.password);
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        back = (ImageView) findViewById(R.id.login_back);
        register= (TextView) findViewById(R.id.register);
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
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_button:
                login();
                break;
            default:
                break;


        }
    }
    public void  login(){
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }
        //验证密码是否正确
        else if (phone.equals("123") && password.equals("123"))

        {
            editor = pref.edit();
            editor.putBoolean("remember_password", true);
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}

