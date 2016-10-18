package com.zm.paipai;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.zm.paipai.adapter.ImageAdapter;
import com.zm.paipai.pinglunactivity.listActivity;
import com.zm.paipai.proj.PathactivityBean;
import com.zm.paipai.proj.Product;
import com.zm.paipai.proj.SpacesItemDecoration;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class fragment_remen extends Fragment {
    private ImageAdapter adpter;
    private RecyclerView recyclerView;
    private List<Product> data;
    private Button bt;
    private ImageAdapter adapter;
    final ArrayList<PathactivityBean.Path> pathlist = new ArrayList<PathactivityBean.Path>();
    private final String baseUrl="http://10.0.2.2:8080/webpro4/image/";
    //用来存储我们需要用到的18个Url地址
    private List<String> urls;
    private ArrayList<String> titles=new ArrayList<>();
    private ArrayList<String> dianzan=new ArrayList<>();
//
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("///////////////////////////");
        View view=inflater.inflate(R.layout.fragment_remen,null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        getBaseUrl();
        initView();
        return view;
    }
    private void getBaseUrl() {
        System.out.println("???????????????????????");
        urls=new ArrayList<String>();
        data = new ArrayList<Product>();
        RequestParams params = new RequestParams("http://10.0.2.2:8080/webpro4/getPath");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                PathactivityBean bean = gson.fromJson(result, PathactivityBean.class);
                pathlist.addAll(bean.pathList);

                for (int i = 0; i < pathlist.size(); i++) {
                    urls.add(baseUrl + pathlist.get(i).path + ".png");
                    titles.add(pathlist.get(i).descp);
                    dianzan.add(pathlist.get(i).dianzan);
            }
                for(int i=0;i<urls.size();i++){
                    Product product = new Product(urls.get(i), titles.get(i),dianzan.get(i));
                    data.add(product);
                }
                adapter.notifyDataSetChanged();
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
    private void initView() {

        System.out.println("???????????/////////////////");
        final StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adpter  = new ImageAdapter(data,getActivity(),R.layout.item);
        adpter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),listActivity.class);
                    //我们需要传递这张图片的Url地址给MainActivity
                    intent.putExtra("imageUrl",urls.get(position));
                    startActivity(intent);
            }
        });
        recyclerView.setAdapter(adpter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);

    }

    }

