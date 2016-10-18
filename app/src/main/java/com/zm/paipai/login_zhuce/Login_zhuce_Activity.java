package com.zm.paipai.login_zhuce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zm.paipai.MainActivity;
import com.zm.paipai.R;

public class Login_zhuce_Activity extends AppCompatActivity  {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_zhuce_);

        bt = ((Button) findViewById(R.id.bt));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_zhuce_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
