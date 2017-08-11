package com.zhuoxin.demo.asynctaskdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/8/9.
 */

public class MyAsyncTask  extends AsyncTask<Integer,Integer,Boolean>{
    private TextView tv ;
    private ProgressBar mProgressBar;
    private Context mContext;
    private ListView mlv;
    private List<String> mlist ;
    protected Myadapter mMyadapter;
    protected String mStr;

    public MyAsyncTask(TextView tv, ProgressBar progressBar ,Context context,ListView lv) {
        this.tv = tv;
        mContext = context;
        mlv = lv;
        mlist = new ArrayList<>();
        mProgressBar = progressBar;


    }

    @Override
    protected void onPreExecute() {

        mMyadapter = new Myadapter(mlist, mContext);
        mlv.setAdapter(mMyadapter);
        Toast.makeText(mContext,"开始下载",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(Integer... params) {

       /* OkhttpUtils.getOkhttp("http://118.244.212.82:9092/newsClient/" + "news_list?ver=1&subid=2&dir=1&nid=新闻id&stamp=20140321&cnt=1", mContext, new OkhttpUtils.CallBack() {
            @Override
            public void successed(Call call, String str) {
                publishProgress(10);
            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });*/
        for (int i = 0; i <= 100; i++) {
            try {
                mStr = "hello world!!!";


                Thread.sleep(100);
                publishProgress(i);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
       int i =  values[0];
        tv.setText(i+"%");
        mlist.add(mStr);
        mProgressBar.setProgress(i);

        mMyadapter.setList(mlist);
        mMyadapter.notifyDataSetChanged();


        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Toast.makeText(mContext,"下载完成",Toast.LENGTH_SHORT).show();
    }
}
