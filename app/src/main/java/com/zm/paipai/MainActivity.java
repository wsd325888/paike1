package com.zm.paipai;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


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
                drawer.closeDrawers();
            }
        });
    }
    private void switchFragment(Fragment fragment) {
        this.getFragmentManager().beginTransaction().replace(R.id.fl_content,fragment).commit();
    }
    }


