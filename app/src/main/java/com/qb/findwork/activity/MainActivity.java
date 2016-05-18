package com.qb.findwork.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.R;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.Person;
import com.qb.findwork.data.UserLogin;
import com.qb.findwork.fragment.MainFragment;
import com.qb.findwork.fragment.ManFragment;
import com.qb.findwork.fragment.WorkFragment;
import com.qb.findwork.util.ActivityManagers;
import com.qb.findwork.util.ChangeLanguage;
import com.qb.findwork.util.HttpGetPerson;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;
import com.qb.findwork.util.SavePic;
import com.qb.findwork.util.ShareDate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pref;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LinearLayout linearlayoutPerson;
    private FloatingActionsMenu floatingActionsMenu;
    private View actionB;
    private View actionA;
    private Toolbar toolbar;
    private boolean isLogin = false;
    private TextView header_name, header_phone;
    private ImageView icon_imageView;
    //private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeLanguage.getLanguage(MainActivity.this);
        Log.i("test","create");
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
        header_name = (TextView) nv.findViewById(R.id.header_name);
        header_phone = (TextView) nv.findViewById(R.id.header_phone);
        icon_imageView= (ImageView) nv.findViewById(R.id.icon_imageView);
        setHeader();
        linearlayoutPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                isLogin = pref.getBoolean("islogin", false);
                if (isLogin == true) {
                    intent = new Intent(MainActivity.this, PersonActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
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

        ChangeLanguage.getLanguage(MainActivity.this);
        ActivityManagers.addActivity(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        actionB = findViewById(R.id.action_b);
        actionA = findViewById(R.id.action_a);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isLogin = pref.getBoolean("islogin", false);
                Intent intent = null;
                if (isLogin) {
                    intent = new Intent(MainActivity.this, CompileWorkActivity.class);

                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                floatingActionsMenu.toggle();
            }
        });
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = pref.getBoolean("islogin", false);
                Intent intent = null;
                if (isLogin) {
                    intent = new Intent(MainActivity.this, CompileEmployActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                floatingActionsMenu.toggle();
            }
        });
        //获取个人资料数据
       // HttpGetPerson.getPerson(MainActivity.this);
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
        isLogin = pref.getBoolean("islogin", false);
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
        }
//        else if (id == R.id.nav_like) {
//            //收藏
//        }
        else if (id == R.id.nav_setting) {

            Intent intent;
            if (isLogin) {
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


    public void setHeader() {

        String name = ShareDate.getString("personName", MainActivity.this);

        if (!name.equals("") && name != null) {
            header_name.setText(name);
        }
        String phone = ShareDate.getString("personPhone", MainActivity.this);

        if (!phone.equals("") && name != null) {
            header_phone.setText(phone);
        }
        String img = SavePic.ALBUM_PATH+"null.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap sdBitmap = BitmapFactory.decodeFile(img, options);
        icon_imageView.setImageBitmap(sdBitmap);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        setHeader();
    }

    @Override
    protected void onDestroy() {
        Log.i("test", "maindestroy");
        super.onDestroy();
        //ActivityManagers.removeActivity(this);
    }

}