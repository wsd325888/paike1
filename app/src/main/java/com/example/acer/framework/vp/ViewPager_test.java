package com.example.acer.framework.vp;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.acer.framework.Login;
import com.example.acer.framework.MainActivity;
import com.example.acer.framework.R;
import com.example.acer.framework.ZhuceActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPager_test extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp;
    int previoutsPosition_vp = 0;
    private Button btn_tiaoguo;
    private Button btn_denglu;
    private Button btn_zhuce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须在setContentView 之前
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_view_pager);
        btn_tiaoguo = ((Button) findViewById(R.id.btn_tiaoguo));
        btn_tiaoguo.setOnClickListener(this);
        btn_denglu = ((Button) findViewById(R.id.btn_denglu));
        btn_denglu.setOnClickListener(this);
        btn_zhuce = ((Button) findViewById(R.id.btn_zhuce));
        btn_zhuce.setOnClickListener(this);
        final int[] imgs = {R.id.iv_iv1,R.id.iv_iv2,R.id.iv_iv3,R.id.iv_iv4};
        vp = ((ViewPager) findViewById(R.id.vp));
        List<Integer> imgsrc=new ArrayList<Integer>();
        imgsrc.add(0,R.drawable.start_i1);
        imgsrc.add(1,R.drawable.start_i2);
        imgsrc.add(2,R.drawable.start_i3);
        imgsrc.add(3,R.drawable.start_i4);
        MyPageAdaper pageAdaper=new MyPageAdaper(imgsrc);
        vp.setAdapter(pageAdaper);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将position位置的换成红色，其他都编程灰色
                /*for (int i = 0;i<imgs.length;i++){
                    ((ImageView) findViewById(imgs[i])).setImageResource(R.drawable.point_gray);
                    if(i==position){
                        ((ImageView) findViewById(imgs[i])).setImageResource(R.drawable.point_red);
                    }
                }*/
                ((ImageView) findViewById(imgs[position])).setImageResource(R.drawable.point_red);
                ((ImageView) findViewById(imgs[previoutsPosition_vp])).setImageResource(R.drawable.point_gray);
                previoutsPosition_vp = position;
//                Toast.makeText(ViewPager_test.this,position+"",Toast.LENGTH_SHORT).show();
                //处理跳过按钮
                if(position==imgs.length-1){
                    btn_tiaoguo.setVisibility(View.VISIBLE);
                    btn_denglu.setVisibility(View.VISIBLE);
                    btn_zhuce.setVisibility(View.VISIBLE);
                }else{

                    btn_tiaoguo.setVisibility(View.GONE);
                    btn_denglu.setVisibility(View.GONE);
                    btn_zhuce.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(3);//改变viewpage当前页
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiaoguo:
            Intent intent = new Intent(ViewPager_test.this, MainActivity.class);
            startActivity(intent);
                break;
            case R.id.btn_denglu:
                Intent intent1=new Intent(ViewPager_test.this, Login.class);
                startActivity(intent1);
                break;
            case R.id.btn_zhuce:
                Intent intent2=new Intent(ViewPager_test.this, ZhuceActivity.class);
                startActivity(intent2);
                break;

        }
    }

    private  class MyPageAdaper extends PagerAdapter{
        List<Integer> imgsrc;
        private ImageView ImageView;


        public  MyPageAdaper(List<Integer> imgsrc){
               this.imgsrc=imgsrc;
           }
        @Override
        public int getCount() {
            return imgsrc.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           View view=View.inflate(getApplicationContext(),R.layout.vp_item,null);
            ImageView iv_vp_item=   ((ImageView) view.findViewById(R.id.iv_vp_item));
            iv_vp_item.setImageResource(imgsrc.get(position));
            container.addView(view);//重要

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View)object);
        }
    }
}
