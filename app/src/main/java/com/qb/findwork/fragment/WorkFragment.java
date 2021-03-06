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
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.adapter.WorkRecyclerViewAdapter;
import com.qb.findwork.data.ListData;
import com.qb.findwork.data.Workdata;
import com.qb.findwork.util.HttpGetWork;

import java.util.ArrayList;
import java.util.List;


public class WorkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private List<Workdata> workdatas;
    private WorkRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        View view=inflater.inflate(R.layout.fragment_work, container, false);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerView_work);
        adapter=new WorkRecyclerViewAdapter(ListData.workWorkList,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.work_swipe_container);
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
                adapter = new WorkRecyclerViewAdapter(ListData.workWorkList, getActivity());
                recyclerView.setAdapter(adapter);
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
