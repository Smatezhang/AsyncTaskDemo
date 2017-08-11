package com.zhuoxin.demo.asynctaskdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class Myadapter extends BaseAdapter {


    private List<String> mList;
    private Context mContext;

    public void setList(List<String> list) {
        mList = list;
    }
    public Myadapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      if (convertView ==null){
          convertView = View.inflate(mContext,R.layout.item,null);
      }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        String mS = mList.get(position);
        tv.setText(mS);

        return convertView;
    }
}
