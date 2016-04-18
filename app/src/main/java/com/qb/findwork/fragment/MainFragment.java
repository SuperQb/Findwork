package com.qb.findwork.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qb.findwork.R;
import com.qb.findwork.adapter.RecyclerViewAdapter;
import com.qb.findwork.data.Workdata;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Workdata> workdatas;
    private RecyclerViewAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        View view=inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerView_main);
        initPersonData();
        adapter=new RecyclerViewAdapter(workdatas,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }


    private void initPersonData() {
        workdatas =new ArrayList<>();
        workdatas.add(new Workdata("测试一","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试二","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试三","王先生",R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试四","王先生",R.mipmap.ulinxinru));
    }


}






