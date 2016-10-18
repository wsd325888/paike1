package com.zm.paipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.zm.paipai.MainActivity;
import com.zm.paipai.R;
import com.zm.paipai.proj.Product;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/29.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

    private List<Product> data;
    private Context context;
    private int resouce_id;
   private OnItemClickListener mOnItemClickListener;
    public ImageAdapter(List<Product> data, Context context, int resouce_id) {
        this.data = data;
        this.context = context;
        this.resouce_id = resouce_id;
    }
    //新建私有变量用于保存用户设置的监听器及其set方法：
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //新建内部接口：
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    @Override
    public ImageAdapter.MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.MyHolder myHolder, final int i) {
        int[] mheigth=new int[]{700,800};
        Picasso.with(context).load(data.get(i).getUrls()).networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE);
        Picasso.with(context).load(data.get(i).getUrls()).placeholder(R.drawable.load).error(R.mipmap.ic_launcher).into(myHolder.img);

        if (data.get(i).getTitle().toString()!=null) {
            myHolder.title.setText(data.get(i).getTitle().toString() + "");
        }
        myHolder.dianzan.setText(data.get(i).getDianzan().toString()+"");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)myHolder.img.getLayoutParams();
        if(i%2==0) {
            layoutParams.height =mheigth[0];
        }else {
            layoutParams.height=mheigth[1];
        }
        myHolder.img.setLayoutParams(layoutParams);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(myHolder.itemView,i); // 2
                }
            });
        }
   }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView dianzan;
        private TextView title;
        private ImageView img;

        public MyHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            dianzan = ((TextView) view.findViewById(R.id.zan));
        }
    }

}

