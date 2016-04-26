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
    private List<Workdata> workdatas;
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
        adapter = new RecyclerViewAdapter(workdatas, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }


    private void initPersonData() {
        workdatas = new ArrayList<>();
        workdatas.add(new Workdata("测试一", "王先生", R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试二", "王先生", R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试三", "王先生", R.mipmap.ulinxinru));
        workdatas.add(new Workdata("测试四", "王先生", R.mipmap.ulinxinru));
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


    public void getJason() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = "http://192.168.0.4:8080/Test/Testt?username=qubo";
                HttpURLConnection connection = HttpUtil.sedHttpRequest(address);
                //发送数据

                //接收数据（是否注册成功，检查重复）
                String jsonData = HttpGetString.HttpgetString(connection);
                Log.i("jsonworkMain", jsonData);
                //parseJSONWithJSONObject(jsonData);
            }
        }).start();
    }

    //解析jason数据，放到list
    public void parseJSONWithJSONObject(String jsonData) {
        Gson gson = new Gson();
        List<Work> WorkList = gson.fromJson(jsonData, new TypeToken<List<UserLogin>>() {
        }.getType());
        for (Work work : WorkList) {

        }

    }
}






