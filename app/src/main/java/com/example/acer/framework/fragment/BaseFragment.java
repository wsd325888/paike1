package com.example.acer.framework.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by acer on 2016/10/9.
 */

public abstract class BaseFragment extends Fragment {

    //1.找控件
    //2.界面数据初始化
    //3.设置时间


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }
    public abstract void initView();//找控件
    public abstract void initEvent();//设置控件的事件
    public abstract void initData();//界面初始化
}
