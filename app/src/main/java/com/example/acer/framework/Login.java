package com.example.acer.framework;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zm.paipai.*;


public class Login extends Activity {
    /**
     * Called when the activity is first created.
     */

    ProgressDialog m_dlog;
    SQLiteDatabase mysql;
    View dialogview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mysql = this.openOrCreateDatabase("student.db", MODE_PRIVATE, null);
        try {
            mysql.execSQL("CREATE TABLE login_t(_id INTEGER PRIMARY KEY,username TEXT,name1 TEXT,password TEXT )");
        } catch (Exception e) {
        }

        LayoutInflater fc = LayoutInflater.from(Login.this);
        final View DialogView = fc.inflate(R.layout.dialog, null);
        Dialog dialog = new AlertDialog.Builder(Login.this)
                .setTitle("登录框")
                .setView(DialogView)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        EditText edit = (EditText) DialogView.findViewById(R.id.username);
                        String name_test = edit.getText().toString();

                        m_dlog = ProgressDialog.show(Login.this,
                                "请稍等", "正在为你登录", true);
//				new Thread()
//				{
//					public void run()
//					{
//						 try {
//							 sleep(3000);
//						 }
//						 catch(Exception e)
//						 {
//							 e.printStackTrace();
//						 }
//						 finally
//						 {
//							 m_dlog.dismiss();
//						 }
//					 }
//				  }.start();


                        m_dlog.dismiss();

                        Cursor cs = mysql.query(true, "login_t", new String[]{"username", "name1"}, "username" + "='" + name_test + "'", null, null, null, null, null);
                        if (cs.getCount() > 0) {
                            m_dlog.dismiss();
                            LayoutInflater fc1 = LayoutInflater.from(Login.this);
                            final View DialogView1 = fc1.inflate(R.layout.tishi, null);
                            cs.moveToFirst();
                            String str = cs.getString(1);
                            TextView textview1 = (TextView) DialogView1.findViewById(R.id.name1);
                            textview1.setText(str);
                            Dialog dialog1 = new AlertDialog.Builder(Login.this)
                                    .setTitle("登录框")
                                    .setView(DialogView1)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Intent intent = new Intent();
                                            intent.setClass(Login.this, com.zm.paipai.MainActivity.class);
                                            startActivity(intent);

                                        }


                                    }).create();
                            dialog1.show();

                        } else {
                            Dialog dialog1 = new AlertDialog.Builder(Login.this)
                                    .setTitle("信息框")
                                    .setMessage("登录失败")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Intent intent = new Intent();
                                            intent.setClass(Login.this, Login.class);
                                            startActivity(intent);
                                            Login.this.finish();

                                        }
                                    }).create();
                            dialog1.show();
                        }
                    }

                })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Login.this.finish();
                            }
                        })
                .setNeutralButton("注册", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent();
                        intent.setClass(Login.this, ZhuceActivity.class);
                        startActivity(intent);
                        Login.this.finish();
                    }
                })
                .create();
        dialog.show();

    }
}

