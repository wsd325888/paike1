package com.zm.paipai.pinglunactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zm.paipai.R;
import com.zm.paipai.proj.ListactivityBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity implements View.OnClickListener {

        private static final String TAG = "listActivity";
        private ListView lv_pinglun;
        private BaseAdapter adapter;
        private TextView tv_content;
        private TextView tv_dianzan;
        // 一个listview对应的list是不可以变化的（引用）
        final ArrayList<ListactivityBean.PingLun> pinglunList = new ArrayList<ListactivityBean.PingLun>();
        private ProgressBar progressbar;
        private RadioButton rb3;
    private TextView tv5;
    private EditText et;
    private ImageView imageView;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);
            lv_pinglun = ((ListView) findViewById(R.id.lv_pinglun));
            progressbar = ((ProgressBar) findViewById(R.id.progressbar));
            rb3 = ((RadioButton) findViewById(R.id.rb3));
            tv5 = ((TextView) findViewById(R.id.tv5));
            et = ((EditText) findViewById(R.id.et));

             et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((TextView) v).setHint("请输入...");
                } else {
                    ((TextView) v).setHint(" ");
                }

            }
        });

        LayoutInflater infla = LayoutInflater.from(this);
        View headView = infla.inflate(R.layout.head_item, null);
        imageView = ((ImageView) headView.findViewById(R.id.iv));
        lv_pinglun.addHeaderView(headView, null, true);

        Intent intent=getIntent();
            String imageUrl=intent.getExtras().getString("imageUrl");
            Picasso.with(this).load(imageUrl).placeholder(R.drawable.amc).error(R.mipmap.ic_launcher).fit().into(imageView);

            rb3.setOnClickListener(this);
            adapter = new BaseAdapter() {

                @Override
                public int getCount() {
                    return pinglunList.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    // 打气筒  view就是指每一个listview item
                    View view = View.inflate(listActivity.this, R.layout.activity, null);
                    View view1=View.inflate(listActivity.this,R.layout.activity_list,null);
                    TextView tv_name = ((TextView) view.findViewById(R.id.tv_name));
                    tv_content = ((TextView) view.findViewById(R.id.tv_content));
                    tv_dianzan = ((TextView) findViewById(R.id.tv_dianzan));
                    ListactivityBean.PingLun pinglun = pinglunList.get(position);
                    tv_name.setText(pinglun.name);
                    tv_content.setText(pinglun.content);
                    tv_dianzan.setText(pinglun.dianzan);
                    return view;
                }
            };
            lv_pinglun.setAdapter(adapter);
            getpinglunList();
        }

    private void getpinglunList() {

        RequestParams params = new RequestParams("http://10.40.5.24:8080/webpro4/getpinglun");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                ListactivityBean bean = gson.fromJson(result, ListactivityBean.class);
                pinglunList.addAll(bean.pinglunlist);
                adapter.notifyDataSetChanged();
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,XinxiActivity.class);
        startActivity(intent);
    }
}

