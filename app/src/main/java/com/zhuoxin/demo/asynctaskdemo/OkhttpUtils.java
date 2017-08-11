package com.zhuoxin.demo.asynctaskdemo;

import android.app.Activity;

import java.io.IOException;



/**
 * Created by Administrator on 2017/7/21.
 */

public class OkhttpUtils {
/*
    public static void getOkhttp(String url , final Activity activity, final CallBack callBack){

        OkHttpClient mOkHttpClient = new OkHttpClient();

        Request mRequest = new Request.Builder()
                .url(url)
                .build();
        Call mCall = mOkHttpClient.newCall(mRequest);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail(call,e);
                    }
                });

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {

                final String result = response.body().string();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.successed(call,result);
                    }
                });
            }
        });


    }

    public interface CallBack{
        void successed(Call call, String str);
        void fail(Call call, IOException e);
    }*/
}
