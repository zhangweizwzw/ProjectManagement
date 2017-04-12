package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.MainActivity;
import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.ManagersBean;
import com.bj.yatu.projectmanagement.model.UserLoginBean;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_center;
    private TextView tv1,tv2,tv3;
    private Button btn_reset,btn_login;
    private EditText account_et,password_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();

    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("登录");

        btn_reset= (Button) findViewById(R.id.btn_reset);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_reset.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        account_et= (EditText) findViewById(R.id.account_et);
        password_et= (EditText) findViewById(R.id.password_et);


        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }


    private void goLogin(String account,String password) {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"verifyuser")
                .addParams("userName",account)
                .addParams("password",password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ToastUtil.showToast(LoginActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        UserLoginBean userLoginBean = gson.fromJson(response, UserLoginBean.class);
//                        UserLoginBean userLoginBean = gson.fromJson(response, new TypeToken<ArrayList<UserLoginBean>>(){}.getType());
                        if(userLoginBean.isStatus()){
                            int identity= userLoginBean.getUser().getRemark();

                           MyApplication.identity= identity;
                           MyApplication.account=userLoginBean.getUser().getAccount();
                           MyApplication.username=userLoginBean.getUser().getUsername();

                           Intent intent=new Intent();
                           if(identity==0||identity==1){
                                intent.setClass(LoginActivity.this,MainActivity.class);
                           }else{
                               intent.setClass(LoginActivity.this,ProjectManagerActivity.class);
                           }
                           startActivity(intent);
                           finish();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reset:
                account_et.setText("");
                password_et.setText("");
                break;
            case R.id.btn_login:
                String account=account_et.getText().toString().trim();
                String password=password_et.getText().toString().trim();
                if(StringUtil.isEmpty(account)){
                    ToastUtil.showToast(this,"请输入账号！");
                }else if(StringUtil.isEmpty(password)){
                    ToastUtil.showToast(this,"请输入密码！");
                }else{
                    goLogin(account,password);
                }
                break;
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
