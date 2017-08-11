package com.zhuoxin.demo.asynctaskdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LoadImage {
    long maxMemory = Runtime.getRuntime().maxMemory() / 8;
    LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>((int) maxMemory) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };


    public LoadImage(Context context, ImageView imageView ,String url) {

        //获取文件名
        String mSubstring = url.substring(url.lastIndexOf("/") + 1);

        //从内存中取
        Bitmap mBitmap = mLruCache.get(mSubstring);
        if (mBitmap != null){
            //设置
            imageView.setImageBitmap(mBitmap);
            return;
        }
        //从本地取
        File mCacheDir = context.getCacheDir();
        if (mCacheDir.exists()&&mCacheDir.isDirectory()){
            File[] mFiles = mCacheDir.listFiles();
            for (File f:mFiles
                 ) {
              if (f.getName().equals(mSubstring)){
                  mBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                  imageView.setImageBitmap(mBitmap);
                  return;
              }
            }

        }else{
            mCacheDir.mkdirs();
        }


        // 网络下载图片
        MyAsyncTask mMyAsyncTask = new MyAsyncTask(context, imageView);
        mMyAsyncTask.execute(url);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private Context mContext;
        private ImageView mImageView;

        public MyAsyncTask(Context context, ImageView imageView) {
            mContext = context;
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            String mSubstring = url.substring(url.lastIndexOf("/") + 1);
            InputStream mInputStream = getBitmap(url);
            if (mInputStream == null) {
                return null;
            }
            Bitmap mBitmap = BitmapFactory.decodeStream(mInputStream);
            if (mLruCache.get(mSubstring) == null) {

                mLruCache.put(mSubstring, mBitmap);
            }
            /**
             * 6.存到本地
             *  6.1 需要获得路径
             *  6.2 保存到这个路径
             */
            //6.1 获得缓存路径
            File mCacheDir = mContext.getCacheDir();

            try {
                //文件输出流

                FileOutputStream mFileOutputStream = new FileOutputStream(new File(mCacheDir, mSubstring));
                //6.2压缩保存
                mBitmap.compress(Bitmap.CompressFormat.PNG,100,mFileOutputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null){

                Toast.makeText(mContext,"连接失败！",Toast.LENGTH_SHORT).show();

            }else{
                //设置图片
                mImageView.setImageBitmap(bitmap);
            }

        }

        /**
         * 网路请求图片
         *
         * @param url
         * @return
         */
        private InputStream getBitmap(String url) {
            OkHttpClient mClient = new OkHttpClient.Builder()
                    .connectTimeout(8, TimeUnit.SECONDS)
                    .readTimeout(8, TimeUnit.SECONDS)
                    .build();
            Request mRequest = new Request.Builder()
                    .url(url)
                    .build();
            Response mResponse = null;

            try {
                mResponse = mClient.newCall(mRequest).execute();
                if (mResponse.isSuccessful()) {
                    return mResponse.body().byteStream();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


    }



}
