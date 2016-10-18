package com.zm.paipai.proj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Product {
    private String urls;
    private String title;
    private String dianzan;


    public Product(String urls,String title,String dianzan) {
        this.urls = urls;
        this.title = title;
        this.dianzan=dianzan;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDianzan() {
        return dianzan;
    }

    public void setDianzan(String dianzan) {
        this.dianzan = dianzan;
    }

    @Override
    public String toString() {
        return "Product{" +
                "urls='" + urls + '\'' +
                ", title='" + title + '\'' +
                ", dianzan='" + dianzan + '\'' +
                '}';
    }
}
