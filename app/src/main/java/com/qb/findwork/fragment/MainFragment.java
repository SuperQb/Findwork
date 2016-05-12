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
import com.qb.findwork.util.HttpUtil;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private List<Work> workdatas;
    private RecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;

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
        initPersonData();
        adapter = new RecyclerViewAdapter(ListData.workList, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }


    private void initPersonData() {

       // workdatas = new ArrayList<>();
//        workdatas.add(new Workdata("测试一", "王先生", R.mipmap.ulinxinru));
//        workdatas.add(new Workdata("测试二", "王先生", R.mipmap.ulinxinru));
//        workdatas.add(new Workdata("测试三", "王先生", R.mipmap.ulinxinru));
//        workdatas.add(new Workdata("测试四", "王先生", R.mipmap.ulinxinru));
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
            }
        }, 5000);
    }


}






