package com.qb.findwork.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.Work;
import com.qb.findwork.util.SavePic;

import java.util.ArrayList;
import java.util.List;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fab;
    private TextView tv_work_name, tv_work_money, tv_work_sex, tv_work_location, tv_work_more_introduce, tv_work_more_content, tv_work_more_required;
    private Button linkman_button;
    private String Id, registerPhone;
    private ImageView backdrop;
    private List<Work> typeWork = new ArrayList<>();
    private String phoneNumber;

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
        linkman_button = (Button) findViewById(R.id.linkman_button);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        linkman_button.setOnClickListener(this);
        setText();

    }

    private void setText() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int number = Integer.parseInt(bundle.getString(RecyclerViewAdapter.NUMBER));
        Id = bundle.getString(RecyclerViewAdapter.ID);
        registerPhone = bundle.getString(RecyclerViewAdapter.REGISTERPHONE);

        String listType = bundle.getString(RecyclerViewAdapter.WORKTYPE);
        if (listType.equals("all")) {

            typeWork = ListData.workList;
        } else if (listType.equals("work")) {
            typeWork = ListData.workWorkList;

        } else if (listType.equals("man")) {
            typeWork = ListData.mankWorkList;
        }

        tv_work_name.setText(typeWork.get(number).getPosition());
        tv_work_money.setText(typeWork.get(number).getPay());
        tv_work_sex.setText(typeWork.get(number).getSex());
        tv_work_location.setText(typeWork.get(number).getLocation());
        tv_work_more_introduce.setText(typeWork.get(number).getIntrduce());
        tv_work_more_content.setText(typeWork.get(number).getContent());
        tv_work_more_required.setText(typeWork.get(number).getRequid());

        phoneNumber=typeWork.get(number).getCallphone();
        String img = SavePic.ALBUM_PATH + Id + registerPhone + ".jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap sdBitmap = BitmapFactory.decodeFile(img, options);
        backdrop.setImageBitmap(sdBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linkman_button:
                callPhone(phoneNumber);
                break;
            default:
                break;
        }
    }
    public  void callPhone(String phoneNumber){

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        Log.i("test",phoneNumber);
        startActivity(intent);

    }

}
