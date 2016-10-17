package com.example.acer.framework;


import android.app.Fragment;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.acer.framework.fragment.Fragment_guanzhu;
import com.example.acer.framework.fragment.Fragment_home;
import com.example.acer.framework.fragment.Fragment_hot;

import static android.R.layout.simple_expandable_list_item_1;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView left_drawer1;
    private RadioGroup rg_tab;
    private DrawerLayout drawer;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        initdata();
    }



    private void initview() {

        left_drawer1 = ((ListView) findViewById(R.id.left_drawer1));
        rg_tab = ((RadioGroup) findViewById(R.id.rg_tab));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
    }

    private void initdata() {

        //默认home显示
        switchFragment(new Fragment_home());
        ImageView imageView= new ImageView(this);
        imageView.setImageResource(R.drawable.fire_red);
        left_drawer1.addHeaderView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Gerenzhuye.class);
                startActivity(intent);
            }
        });
        left_drawer1.setAdapter(new ArrayAdapter<String>(MainActivity.this, simple_expandable_list_item_1,new String[]{"个人资料","搜索","私信","订单详情"}));

        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {

                    case R.id.rb_rb1:
                        Log.i(TAG,"选中关注tab");
                        fragment = new Fragment_guanzhu();
                        break;
                    case R.id.rb_rb2:
                        Log.i(TAG,"选中热门tab");
                        fragment = new Fragment_hot();
                        break;
                    case R.id.rb_rb3:
                        Log.i(TAG,"选中首页tab");  fragment = new Fragment_guanzhu();
                        fragment = new Fragment_home();
                        break;
                }



                switchFragment(fragment);
            }
        });

        left_drawer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,position+"", Toast.LENGTH_SHORT).show();
                switch (position){
                    case 1:
                        Intent intent=new Intent(MainActivity.this,GetpersonInfo.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1=new Intent(MainActivity.this,Sousuoinfo.class);
                        startActivity(intent1);
                        break;
                    case 3:



                }
                drawer.closeDrawers();

            }
        });

    }

    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_content,fragment).commit();
    }
}

