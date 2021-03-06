package com.zm.paipai;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.acer.framework.Gerenzhuye;
import com.example.acer.framework.GetpersonInfo;
import com.example.acer.framework.Sousuoinfo;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private RadioGroup rg_tab;
    private ListView left_drawer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_tab = ((RadioGroup) findViewById(R.id.rg_tab));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        left_drawer1 = ((ListView) findViewById(R.id.left_drawer1));

        switchFragment(new fragment_remen());
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

        left_drawer1.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,new String[]{"私信","查找","设置","本地作品"}));
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {
                    case R.id.rb_rb1:
                        fragment = new fragment_guanzhu();
                        break;
                    case R.id.rb_rb2:
                        fragment = new fragment_remen();
                        break;
                    case R.id.rb_rb3:
                        fragment = new fragment_fujin();
                        break;
                    case R.id.rb_rb4:
                        fragment=new fragment_cramera();
                        break;
                }
                switchFragment(fragment);
            }
        });

        left_drawer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intent = new Intent(MainActivity.this, GetpersonInfo.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(MainActivity.this, Sousuoinfo.class);
                        startActivity(intent1);
                        break;
                }
                drawer.closeDrawers();
            }
        });
        }
    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_content,fragment).commit();
    }
    }


