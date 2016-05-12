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
        initPersonData();
        adapter=new WorkRecyclerViewAdapter(ListData.workList,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.work_swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }

    private void initPersonData() {
        workdatas =new ArrayList<>();
        workdatas.add(new Workdata("给工作一","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("给工作二","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("给工作三","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("给工作四","王先生",R.mipmap.ulinxinru));
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
