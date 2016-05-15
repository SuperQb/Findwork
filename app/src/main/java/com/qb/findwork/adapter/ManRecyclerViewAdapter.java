package com.qb.findwork.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.activity.LoginActivity;
import com.qb.findwork.activity.WorkActivity;
import com.qb.findwork.data.Work;
import com.qb.findwork.util.GetImageStream;
import com.qb.findwork.util.NetIsWifi;
import com.qb.findwork.util.SavePic;
import com.qb.findwork.util.ShareDate;

import java.io.IOException;
import java.util.List;


public class ManRecyclerViewAdapter extends RecyclerView.Adapter<ManRecyclerViewAdapter.NewsViewHolder> {
    public static String LISTTYPE = "man";
    private List<Work> workdatas;
    private Context context;
    private String netType;

    public ManRecyclerViewAdapter(List<Work> workdatas, Context context) {
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
            work_pay = (TextView) itemView.findViewById(R.id.work_pay);

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
        personViewHolder.work_photo.setImageResource(R.drawable.ulinxinru);
        netType= ShareDate.getString(NetIsWifi.NETTYPE, context);

        if (netType.equals(NetIsWifi.NETALL)||NetIsWifi.isWifiConnected(context)) {
            LAsync task = new LAsync(personViewHolder.work_photo);
            task.execute(i);
        }
        String type = workdatas.get(i).getType();
        if (type.equals("2")) {
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
                        String Id = workdatas.get(j).getId();
                        String registerPhone = workdatas.get(j).getPhone();
                        intent = new Intent(context, WorkActivity.class);
                        intent.putExtra(RecyclerViewAdapter.NUMBER, j + "");
                        intent.putExtra(RecyclerViewAdapter.ID, Id);
                        intent.putExtra(RecyclerViewAdapter.REGISTERPHONE, registerPhone);
                        intent.putExtra(RecyclerViewAdapter.WORKTYPE, LISTTYPE);
                        Log.i("test", j + "");

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

    private class LAsync extends AsyncTask<Integer, String, String> {

        private ImageView mImageView;
        String number;
        String registerPhone;

        public LAsync(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Integer... params) {
            // String filePath = "http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg";
            String filePath = workdatas.get(params[0]).getPic();
            Log.i("test", filePath);
            number = workdatas.get(params[0]).getId();
            registerPhone = workdatas.get(params[0]).getPhone();
            try {
                Bitmap mBitmap = BitmapFactory.decodeStream(GetImageStream.getImageStream(filePath));
                //String FileName = params[0];
                String FileName = number + registerPhone + ".jpg";
                SavePic.saveFile(mBitmap, FileName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {


            String img = SavePic.ALBUM_PATH + number + registerPhone + ".jpg";
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap sdBitmap = BitmapFactory.decodeFile(img, options);
            mImageView.setImageBitmap(sdBitmap);

        }

    }
}
