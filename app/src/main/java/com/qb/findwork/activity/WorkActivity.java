package com.qb.findwork.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.data.ListData;

public class WorkActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fab;
    private TextView tv_work_name, tv_work_money, tv_work_sex, tv_work_location, tv_work_more_introduce, tv_work_more_content, tv_work_more_required;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        init();
        collapsingToolbarLayout.setTitle("找工作");


    }

    public void init() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_chevron_left_white_24dp));
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_work_name = (TextView) findViewById(R.id.tv_work_name);
        tv_work_money = (TextView) findViewById(R.id.tv_work_money);
        tv_work_sex = (TextView) findViewById(R.id.tv_work_sex);
        tv_work_location = (TextView) findViewById(R.id.tv_work_location);
        tv_work_more_introduce = (TextView) findViewById(R.id.tv_work_more_introduce);
        tv_work_more_content = (TextView) findViewById(R.id.tv_work_more_content);
        tv_work_more_required = (TextView) findViewById(R.id.tv_work_more_required);
        setText();

    }

    private void setText() {
        Bundle bundle = new Bundle();
        int number = Integer.parseInt(bundle.getString(RecyclerViewAdapter.NUMBER));
        tv_work_name.setText(ListData.workList.get(number).getPosition());
        tv_work_money.setText(ListData.workList.get(number).getPay());
        tv_work_sex.setText(ListData.workList.get(number).getSex());
        tv_work_location.setText(ListData.workList.get(number).getLocation());
        tv_work_more_introduce.setText(ListData.workList.get(number).getContent());
        tv_work_more_content.setText(ListData.workList.get(number).getContent());
        tv_work_more_required.setText(ListData.workList.get(number).getRequid());

    }

}
