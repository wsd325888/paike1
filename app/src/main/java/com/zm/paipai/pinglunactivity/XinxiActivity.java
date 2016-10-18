package com.zm.paipai.pinglunactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zm.paipai.R;
import com.zm.paipai.proj.XinxiactivityBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class XinxiActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv3;
    final ArrayList<XinxiactivityBean.Information> inlist=new ArrayList<XinxiactivityBean.Information>();
    private TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinxi);
        tv1 = ((TextView) findViewById(R.id.tv1));
        tv3 = ((TextView) findViewById(R.id.tv3));
        tv4 = ((TextView) findViewById(R.id.tv4));

        getInformationlist();
    }

    private void getInformationlist() {
        RequestParams params = new RequestParams("http://10.0.2.2:8080/webpro4/getinformation");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                XinxiactivityBean bean = gson.fromJson(result, XinxiactivityBean.class);
                inlist.addAll(bean.informationList);
                XinxiactivityBean.Information infor=inlist.get(0);
                tv1.setText(infor.fensishu);
                tv3.setText(infor.guanzhuren);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
