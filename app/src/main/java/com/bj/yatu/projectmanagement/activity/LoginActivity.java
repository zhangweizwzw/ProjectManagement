package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.MainActivity;
import com.bj.yatu.projectmanagement.R;

import org.w3c.dom.Text;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_center;
    private TextView tv1,tv2,tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();

    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("登录");

        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv3:
                startActivity(new Intent(this, ProjectManagerActivity.class));
                break;
        }
    }
}
