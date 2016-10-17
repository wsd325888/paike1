package com.example.acer.framework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.acer.framework.R;
import com.example.acer.framework.Application.MyApplication;

/**
 * Created by dliu on 2016/10/9.
 */
public class EditNameFragment extends BaseFragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit_name, null);

        EditText etName= (EditText) v.findViewById(R.id.et_edit_name);


       // 初始化姓名
        etName.setText(((MyApplication)getActivity().getApplication()).getUser().getUserName());


        return v;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
