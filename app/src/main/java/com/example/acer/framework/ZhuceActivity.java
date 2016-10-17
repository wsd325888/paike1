package com.example.acer.framework;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ZhuceActivity extends Activity {
    /** Called when the activity is first created. */

    ProgressDialog m_dlog;
    SQLiteDatabase mysql;
    View dialogview;
    Button bt1;
    Button bt2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        bt1=(Button)this.findViewById(R.id.bt1);
        bt2=(Button)this.findViewById(R.id.bt2);
        final EditText name1=(EditText)this.findViewById(R.id.name1);
        final EditText username=(EditText)this.findViewById(R.id.username);
        final EditText password=(EditText)this.findViewById(R.id.password);
        mysql=openOrCreateDatabase("student.db", MODE_PRIVATE,null);
        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if((name1.getText().toString().trim().length()==0)||(username.getText().toString().trim().length()==0)||(password.getText().toString().trim().length()==0))
                {
                    Dialog dialog1=new AlertDialog.Builder(ZhuceActivity.this)
                            .setTitle("信息框")
                            .setMessage("请输入完整的内容")
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1)
                                {}
                            }).create();
                    dialog1.show();
                }
                else
                {try{

                    Cursor m_cus = mysql.rawQuery("select max(_id) from login_t", null);
                    m_cus.moveToFirst();

                    int temp_id = m_cus.getInt(0) + 1;

                    mysql.execSQL("INSERT INTO login_t(_id,username,name1,password) values("+temp_id+",'"+username.getText().toString().trim()+"','"+name1.getText().toString().trim()+"','"+password.getText().toString().trim()+"')");

                    mysql.close();
                    LayoutInflater fac2 = LayoutInflater.from(ZhuceActivity.this);
                    View dialogview2 = fac2.inflate(R.layout.tishi, null);

                    TextView view_1 = (TextView)dialogview2.findViewById(R.id.textView1);
                    view_1.setText("注册成功！");

                    AlertDialog dialog2 = new AlertDialog.Builder(ZhuceActivity.this)
                            .setTitle("提示框")
                            .setView(dialogview2)
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Intent intent = new Intent();
                                            intent.setClass(ZhuceActivity.this, Login.class);
                                            startActivity(intent);
                                            ZhuceActivity.this.finish();
                                        }
                                    }).create();
                    dialog2.show();
                }
                catch(Exception e)
                {
                    Dialog dialog3=new AlertDialog.Builder(ZhuceActivity.this)
                            .setTitle("信息框")
                            .setMessage("注册失败")   .setPositiveButton("确定",new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1)
                                {}


                            }).create();
                    dialog3.show();
                }
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener()
                               {

                                   public void onClick(View v) {
                                       // TODO Auto-generated method stub
                                       ZhuceActivity.this.finish();
                                   }

                               }

        );


    }
}

