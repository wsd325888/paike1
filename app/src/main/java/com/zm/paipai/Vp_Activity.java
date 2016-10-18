package com.zm.paipai;

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

import com.zm.paipai.login_zhuce.Login_zhuce_Activity;

import java.util.ArrayList;
import java.util.List;

public class Vp_Activity extends AppCompatActivity implements View.OnClickListener{
    int previoutsPosition_vp = 0;
    private Button btn_tiaoguo;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);

        btn_tiaoguo = (Button) findViewById(R.id.btn_tiaoguo);
        btn_tiaoguo.setOnClickListener(this);
        final int[] imgs = {R.id.iv_iv1, R.id.iv_iv2, R.id.iv_iv3, R.id.iv_iv4};

        vp = (ViewPager) findViewById(R.id.vp);
        List<Integer> imgsrc = new ArrayList<Integer>();
        imgsrc.add(0, R.drawable.start_i1);
        imgsrc.add(1, R.drawable.start_i2);
        imgsrc.add(2, R.drawable.start_i3);
        imgsrc.add(3, R.drawable.start_i4);
        MyPageAdapter pageAdapter = new MyPageAdapter(imgsrc);
        vp.setAdapter(pageAdapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((ImageView) findViewById(imgs[position])).setImageResource(R.drawable.point_red);
                ((ImageView) findViewById(imgs[previoutsPosition_vp])).setImageResource(R.drawable.point_gray);
                previoutsPosition_vp = position;
                if(position==imgs.length-1){
                    btn_tiaoguo.setVisibility(View.VISIBLE);
                }else{

                    btn_tiaoguo.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Vp_Activity.this, Login_zhuce_Activity.class);
        startActivity(intent);
    }

    private class MyPageAdapter extends PagerAdapter {

        List<Integer> imgsrc;

        public MyPageAdapter(List<Integer> imgsrc) {
            this.imgsrc = imgsrc;
        }

        @Override
        public int getCount() {
            return imgsrc.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            View view  = View.inflate(getApplicationContext(),R.layout.vp_activity,null);
            ImageView iv_vp_item = ((ImageView) view.findViewById(R.id.iv_vp_item));
            iv_vp_item.setImageResource(imgsrc.get(position));
            vp.addView(view);

            return view;
        }
    }
    }

