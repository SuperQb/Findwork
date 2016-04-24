package com.qb.findwork.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.qb.findwork.R;
import com.qb.findwork.fragment.MainFragment;
import com.qb.findwork.fragment.ManFragment;
import com.qb.findwork.fragment.WorkFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    LinearLayout linearlayoutPerson;
    FloatingActionsMenu floatingActionsMenu;
    View actionB;
    View actionA;
    Toolbar toolbar;

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
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
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
        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        actionB = findViewById(R.id.action_b);
        actionA = findViewById(R.id.action_a);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
                Intent intent=new Intent(MainActivity.this,CompileWorkActivity.class);
                startActivity(intent);
            }
        });
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CompileEmployActivity.class);
                startActivity(intent);
                floatingActionsMenu.toggle();
            }
        });
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}