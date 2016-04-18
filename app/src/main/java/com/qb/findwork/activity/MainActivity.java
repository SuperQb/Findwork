package com.qb.findwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.qb.findwork.R;
import com.qb.findwork.fragment.MainFragment;
import com.qb.findwork.fragment.ManFragment;
import com.qb.findwork.fragment.WorkFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    LinearLayout linearlayoutPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();


        NavigationView nv= (NavigationView) findViewById(R.id.nav_view);
        View headerView = nv.getHeaderView(0);
        linearlayoutPerson= (LinearLayout) headerView.findViewById(R.id.person);
        linearlayoutPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PersonActivity.class);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //侧边栏点击
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        transaction = fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action
            Toast.makeText(this, "点击成功", Toast.LENGTH_SHORT).show();
            MainFragment fragmentMain = new MainFragment();
            //transaction.add(fragment);
            transaction.replace(R.id.container, fragmentMain);
            transaction.commit();
        } else if (id == R.id.nav_work) {
            WorkFragment fragmentWork = new WorkFragment();
            //transaction.add(fragment);
            //transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragmentWork);
            transaction.commit();

        } else if (id == R.id.nav_man) {
//            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//            startActivity(intent);
            ManFragment fragmentMan = new ManFragment();
            //transaction.add(fragment);
            //transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragmentMan);
            transaction.commit();


        } else if (id == R.id.nav_like) {
            //跳转收藏页面
//            Intent intent=new Intent(MainActivity.this,PersonActivity.class);
//            startActivity(intent);

        } else if (id == R.id.nav_setting) {
            //设置页面
//            Intent intent=new Intent(MainActivity.this,PersonActivity.class);
//            startActivity(intent);

    }
        else if (id == R.id.nav_about) {
            //关于页面
//            Intent intent=new Intent(MainActivity.this,PersonActivity.class);
//            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
