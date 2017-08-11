package com.zhuoxin.demo.asynctaskdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadImageActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.ed)
    EditText mEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);



    }
    public void click(View v ){
        String mTrim = mEd.getText().toString().trim();
        LoadImage mLoadImage = new LoadImage(this, mImage, mTrim);
    }


}
