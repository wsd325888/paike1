package com.example.acer.framework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gerenzhuye extends AppCompatActivity {

    private Button bt_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhuye);
        bt_01 = ((Button) findViewById(R.id.bt_01));
        bt_01.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Gerenzhuye.this,GetpersonInfo.class);
                startActivity(intent);
            }
        });
    }
}
