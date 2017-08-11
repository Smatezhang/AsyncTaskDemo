package com.zhuoxin.demo.asynctaskdemo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 */

public class NewsInfo implements Serializable {

    private int type;
    private int nid;
    private String stamp;
    private String icon;
    private String title;
    private String summary;
    private String link;

    public NewsInfo(int type, int nid, String stamp, String icon, String title, String summary, String link) {
        this.type = type;
        this.nid = nid;
        this.stamp = stamp;
        this.icon = icon;
        this.title = title;
        this.summary = summary;
        this.link = link;
    }

    public NewsInfo(int nid, String stamp, String icon, String title, String summary, String link) {
        this.nid = nid;
        this.stamp = stamp;
        this.icon = icon;
        this.title = title;
        this.summary = summary;
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public int getNid() {
        return nid;
    }

    public String getStamp() {
        return stamp;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "type=" + type +
                ", nid=" + nid +
                ", stamp='" + stamp + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
