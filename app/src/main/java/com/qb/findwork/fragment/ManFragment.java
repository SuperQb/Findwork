package com.qb.findwork.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qb.findwork.R;
import com.qb.findwork.adapter.ManRecyclerViewAdapter;
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.Workdata;
import com.qb.findwork.util.HttpGetWork;

import java.util.ArrayList;
import java.util.List;


public class ManFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private List<Workdata> workdatas;
    private ManRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        View view=inflater.inflate(R.layout.fragment_man, container, false);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerView_man);
        adapter=new ManRecyclerViewAdapter(ListData.mankWorkList,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.man_swipe_container);
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
                adapter = new ManRecyclerViewAdapter(ListData.mankWorkList, getActivity());
                recyclerView.setAdapter(adapter);
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
