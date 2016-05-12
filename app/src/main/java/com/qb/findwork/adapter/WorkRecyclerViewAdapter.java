package com.qb.findwork.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.activity.LoginActivity;
import com.qb.findwork.activity.WorkActivity;
import com.qb.findwork.data.Work;

import java.util.List;


public class WorkRecyclerViewAdapter extends RecyclerView.Adapter<WorkRecyclerViewAdapter.NewsViewHolder> {

    private List<Work> workdatas;
    private Context context;

    public WorkRecyclerViewAdapter(List<Work> workdatas, Context context) {
        this.workdatas = workdatas;
        this.context = context;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView work_photo;
        TextView work_position;
        TextView work_linkman;
        TextView work_pay;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            work_photo = (ImageView) itemView.findViewById(R.id.work_photo);
            work_position = (TextView) itemView.findViewById(R.id.work_position);
            work_linkman = (TextView) itemView.findViewById(R.id.work_linkman);
            work_pay= (TextView) itemView.findViewById(R.id.work_pay);

        }


    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_main, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        final int j = i;

        String type=workdatas.get(i).getType();
        if(type.equals("1")) {
            personViewHolder.work_photo.setImageResource(R.drawable.ulinxinru);
            personViewHolder.work_position.setText(workdatas.get(i).getPosition());
            personViewHolder.work_linkman.setText(workdatas.get(i).getLinkman());
            personViewHolder.work_pay.setText(workdatas.get(i).getPay());

            personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    boolean isLogin = pref.getBoolean("remember_password", false);
                    Intent intent;
                    if (isLogin == true) {
                        intent = new Intent(context, WorkActivity.class);
                        intent.putExtra(RecyclerViewAdapter.NUMBER, j + "");

                    } else {

                        intent = new Intent(context, LoginActivity.class);

                    }//intent.putExtra("News",newses.get(j));
                    context.startActivity(intent);
                    //Log.i("点击",j+"");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return workdatas.size();
    }
}
