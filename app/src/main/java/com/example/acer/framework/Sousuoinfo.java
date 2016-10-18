package com.example.acer.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;

import com.example.acer.framework.entity.GetsousuoBean;
import com.zm.paipai.R;

import java.util.ArrayList;

public class Sousuoinfo extends AppCompatActivity {
    private BaseAdapter adapter;
    final ArrayList<GetsousuoBean.Sousuo> sslist=new ArrayList<GetsousuoBean.Sousuo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuoinfo);
    }
}
