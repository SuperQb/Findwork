package com.qb.findwork.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.R;
import com.qb.findwork.data.Person;
import com.qb.findwork.data.UserLogin;
import com.qb.findwork.fragment.MainFragment;
import com.qb.findwork.fragment.ManFragment;
import com.qb.findwork.fragment.WorkFragment;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  SharedPreferences pref;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LinearLayout linearlayoutPerson;
    private FloatingActionsMenu floatingActionsMenu;
    private View actionB;
    private View actionA;
    private Toolbar toolbar;
    private boolean isLogin = false;
    public static List<Person> personList;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        //View headerView = nv.getHeaderView(0);
        linearlayoutPerson = (LinearLayout) nv.findViewById(R.id.person);
        linearlayoutPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                isLogin = pref.getBoolean("remember_password", false);
                if (isLogin == true) {
                    intent = new Intent(MainActivity.this, PersonActivity.class);
                }
                else{
                    intent=new Intent(MainActivity.this,LoginActivity.class);
                }
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
        transaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    public void init() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        actionB = findViewById(R.id.action_b);
        actionA = findViewById(R.id.action_a);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
                Intent intent = new Intent(MainActivity.this, CompileWorkActivity.class);
                startActivity(intent);
            }
        });
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompileEmployActivity.class);
                startActivity(intent);
                floatingActionsMenu.toggle();
            }
        });
        getPerson();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //侧边栏点击
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        isLogin = pref.getBoolean("remember_password", false);
        transaction = fragmentManager.beginTransaction();
        int id = item.getItemId();
        if (id == R.id.nav_main) {
            MainFragment fragmentMain = new MainFragment();
            transaction.replace(R.id.container, fragmentMain);
            transaction.commit();
        } else if (id == R.id.nav_work) {
            WorkFragment fragmentWork = new WorkFragment();
            transaction.replace(R.id.container, fragmentWork);
            transaction.commit();
        } else if (id == R.id.nav_man) {
            ManFragment fragmentMan = new ManFragment();
            transaction.replace(R.id.container, fragmentMan);
            transaction.commit();
        } else if (id == R.id.nav_like) {
            //收藏
        } else if (id == R.id.nav_setting) {

            Intent intent;
            if (isLogin == true) {
                Log.i("islogin", isLogin + "");
                intent = new Intent(MainActivity.this, SettingsActivity.class);
            } else {
                Log.i("islogin", isLogin + "");
                intent = new Intent(MainActivity.this, LoginActivity.class);
            }
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getPerson() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //向服务器发送注册时候的手机号，服务器进行查询，返回个人详细资料
                String phone = pref.getString("phone", "NO");
                String address = HttpUtil.ipUrl+"Testt2?registerPhone="+phone;
                HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonData", jsonData);
                parseJSONWithJSONObject(jsonData);
            }
        }).start();

    }
    public void parseJSONWithJSONObject(String jsonData) {


        Gson gson = new Gson();
        personList = gson.fromJson(jsonData, new TypeToken<List<Person>>() {
        }.getType());
        for (Person person : personList) {
            editor = pref.edit();
            editor.putString("personName", person.getName());
            editor.putString("personSex",  person.getSex());
            editor.putString("personAge", person.getAge());
            editor.putString("personPhone", person.getPhone());
            editor.commit();

        }

    }

}