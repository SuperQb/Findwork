package com.qb.findwork.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qb.findwork.R;
import com.qb.findwork.util.DataClearManager;
import com.qb.findwork.util.GetImageStream;
import com.qb.findwork.util.HttpGetString;
import com.qb.findwork.util.HttpUtil;
import com.qb.findwork.util.SavePic;
import com.qb.findwork.util.ShareDate;
import com.qb.findwork.view.CircleView;
import com.qb.findwork.view.WheelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

import me.drakeet.materialdialog.MaterialDialog;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PLANETS = new String[]{"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};
    private RelativeLayout Relat_name, Relat_sex, Relat_age, Relat_phone;
    private TextView per_name, per_sex, per_age, per_phone;
    private ImageView back;

    private SharedPreferences pref;
    private String name, age, sex, phone;
    private TextView perSave;
    private CircleView per_photo;
    private String registerPhone;

    public static final int TAKE_PHOTO = 1;

    public static final int CROP_PHOTO = 2;

    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }


    public void init() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Relat_name = (RelativeLayout) findViewById(R.id.Relat_name);
        Relat_sex = (RelativeLayout) findViewById(R.id.Relat_sex);
        Relat_age = (RelativeLayout) findViewById(R.id.Relat_age);
        Relat_phone = (RelativeLayout) findViewById(R.id.Relat_phone);
        per_photo = (CircleView) findViewById(R.id.per_photo);
        per_name = (TextView) findViewById(R.id.per_name);
        per_sex = (TextView) findViewById(R.id.per_sex);
        per_age = (TextView) findViewById(R.id.per_age);
        per_phone = (TextView) findViewById(R.id.per_phone);
        back = (ImageView) findViewById(R.id.per_back);
        perSave = (TextView) findViewById(R.id.person_save);
        Relat_name.setOnClickListener(this);
        Relat_phone.setOnClickListener(this);
        Relat_sex.setOnClickListener(this);
        Relat_age.setOnClickListener(this);
        back.setOnClickListener(this);
        perSave.setOnClickListener(this);
        per_photo.setOnClickListener(this);
        setPerson();
        new LAsync().execute();
    }



    public void setPerson() {

        phone = pref.getString("personPhone", "10086");
        name = pref.getString("personName", "兼职校园");
        age = pref.getString("personAge", "18");
        sex = pref.getString("personSex", "男");
        phone = pref.getString("personPhone", "10086");
        per_name.setText(name);
        per_age.setText(age);
        per_sex.setText(sex);
        per_phone.setText(phone);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.per_back:
                finish();
                break;
            case R.id.Relat_name:
                Intent intentName = new Intent(PersonActivity.this, NameActivity.class);
                startActivity(intentName);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_phone:
                Intent intentPhone = new Intent(PersonActivity.this, PhoneActivity.class);
                startActivity(intentPhone);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_sex:
                Intent intentSex = new Intent(PersonActivity.this, SexActivity.class);
                startActivity(intentSex);
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.Relat_age:

                View outerView = LayoutInflater.from(this).inflate(R.layout.activity_age, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_wv);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                age = pref.getString("personAge", "18");
                int intAge=Integer.parseInt(age);
                wv.setSeletion(intAge-16);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        age = item;
                    }
                });
                final MaterialDialog mMaterialDialog = new MaterialDialog(this);
                mMaterialDialog.setTitle("年龄");
                mMaterialDialog.setView(outerView);
                mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        Log.i("test", age);
                        per_age.setText(age);
                        ShareDate.setString("personAge", age.toString(), PersonActivity.this);

                    }
                });
                mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();

                    }
                });

                mMaterialDialog.show();
                break;
            case R.id.person_save:
                sendPerson();

                break;
            case R.id.per_photo:
                File outputImage = new File(Environment.getExternalStorageDirectory(),
                        "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CROP_PHOTO);
                break;
            default:
                break;

        }
    }

    public void sendPerson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                 registerPhone = ShareDate.getString("phone",PersonActivity.this);
                String address = HttpUtil.ipUrl + "SavePerson?registerPhone=" + registerPhone
                        + "&phone=" + phone
                        + "&name=" + name
                        + "&icon=" + "icon"
                        + "&age=" + age
                        + "&sex=" + sex;
                HttpUtil.sedHttpRequest(address);
                finish();
            }
        }).start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setPerson();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //HttpUtil.closeHttp();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        per_photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private class LAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
           // String filePath = "http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg";
            String filePath = pref.getString("personIcon", "NO");
            try {
                Bitmap mBitmap = BitmapFactory.decodeStream(GetImageStream.getImageStream(filePath));
                //String FileName = params[0];
                String FileName = registerPhone+".jpg";
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


            String img = SavePic.ALBUM_PATH+registerPhone+".jpg";
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap sdBitmap = BitmapFactory.decodeFile(img, options);
            per_photo.setImageBitmap(sdBitmap);

        }

    }
}
