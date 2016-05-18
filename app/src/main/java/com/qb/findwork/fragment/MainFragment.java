package com.qb.findwork.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qb.findwork.R;
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.UserLogin;
import com.qb.findwork.data.Work;
import com.qb.findwork.data.Workdata;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpGetWork;
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private List<Work> workdatas;
    private RecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;
    private static final int REFRESH_COMPLETE = 0X110;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:

                    adapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_main);
        adapter = new RecyclerViewAdapter(ListData.workList, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }


    @Override
    public void onRefresh() {
        HttpGetWork.getWork();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter = new RecyclerViewAdapter(ListData.workList, getActivity());
                recyclerView.setAdapter(adapter);
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }


}






