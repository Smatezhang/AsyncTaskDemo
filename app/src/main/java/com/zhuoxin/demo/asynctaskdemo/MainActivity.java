package com.zhuoxin.demo.asynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.pb)
    ProgressBar mPb;
    @BindView(R.id.lv)
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MyAsyncTask mMyAsyncTask = new MyAsyncTask(mTv, mPb, this,mLv);
        mMyAsyncTask.execute();
    }

    private List<String> getData() {
        ArrayList<String> mStrings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            String str = "hello world!";
            mStrings.add(str);

        }

        return mStrings;
    }


}