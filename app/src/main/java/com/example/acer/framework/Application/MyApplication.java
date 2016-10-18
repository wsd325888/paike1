package com.example.acer.framework.Application;

import android.app.Application;
import android.os.UserManager;

import com.example.acer.framework.entity.User;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by acer on 2016/9/29.
 */

public class MyApplication extends Application {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user=new User(1);//设置一个默认的用户：id=1

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }



}