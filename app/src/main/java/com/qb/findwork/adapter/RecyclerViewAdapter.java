package com.qb.findwork.adapter;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.data.Workdata;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder> {

    private List<Workdata> workdatas;
    private Context context;

    public RecyclerViewAdapter(List<Workdata> workdatas, Context context) {
        this.workdatas = workdatas;
        this.context = context;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView news_photo;
        TextView news_title;
        TextView news_desc;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            news_photo = (ImageView) itemView.findViewById(R.id.news_photo);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_desc = (TextView) itemView.findViewById(R.id.news_desc);
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

        personViewHolder.news_photo.setImageResource(workdatas.get(i).getPhotoId());
        personViewHolder.news_title.setText(workdatas.get(i).getTitle());
        personViewHolder.news_desc.setText(workdatas.get(i).getMan());

        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context,NewsActivity.class);
//                intent.putExtra("News",newses.get(j));
//                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return workdatas.size();
    }
}
