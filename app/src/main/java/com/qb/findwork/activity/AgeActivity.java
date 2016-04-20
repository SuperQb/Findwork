package com.qb.findwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qb.findwork.R;
import com.qb.findwork.view.WheelView;

import java.util.Arrays;

public class AgeActivity extends AppCompatActivity {
    private static final String[] PLANETS = new String[]{"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        WheelView wva = (WheelView) findViewById(R.id.wheel_wv);
        wva.setOffset(1);
        wva.setItems(Arrays.asList(PLANETS));
        wva.setSeletion(4);
        wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {

            }
        });

    }
}
