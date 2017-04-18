package com.bj.yatu.projectmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.MainActivity;
import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.UserLoginBean;
import com.bj.yatu.projectmanagement.utils.Netutil;
import com.bj.yatu.projectmanagement.utils.ProcessUtil;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="LoginActivity";
    private TextView text_center;
    private TextView btn_reset,btn_login;
    private EditText account_et,password_et;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showConectionDialog();
                    break;
                case 2:

                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        /**
         * 是否第一次今入主界面
         * 判断是否有网络
         * 没有，弹出设置网络框
         */
        if(MyApplication.isFirstMain){
            if(Netutil.isNetworkAvailable(LoginActivity.this)){
                checkconection();
            }else{
                showNetAlert();
            }
            MyApplication.isFirstMain=false;
        }
    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("登录");

        btn_reset= (TextView) findViewById(R.id.btn_reset);
        btn_login= (TextView) findViewById(R.id.btn_login);
        btn_reset.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        account_et= (EditText) findViewById(R.id.account_et);
        password_et= (EditText) findViewById(R.id.password_et);

    }

    /**
     * 网络设置对话框
     */
    private void showNetAlert() {
        Log.i("TAG","进入alert设置");
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("提示");
        alert.setMessage("当前无网络，是否设置网络？");
        alert.setCancelable(false);
        alert.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 1);
            }
        });
        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.create();
        alert.show();
    }

    /**
     * 判断服务器是否连接
     */
    private void checkconection() {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.i(TAG, "服务器连接失败");

                        mHandler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "服务器连接成功");
                        mHandler.sendEmptyMessage(2);
                    }
                });
    }


    private void goLogin(String account,String password) {
        ProcessUtil.showProcess(this,"正在登录，请稍后...");
        OkHttpUtils
            .post()
            .url(RequstUrls.REQUEST_URL+"verifyuser")
            .addParams("account",account)
            .addParams("password",password)
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    ProcessUtil.dismiss();
                    ToastUtil.showToast(LoginActivity.this,"请求失败！请检查网络设置");
                }

                @Override
                public void onResponse(String response) {
                    ProcessUtil.dismiss();
                    Log.i(TAG,response);
                    Gson gson=new Gson();
                    UserLoginBean userLoginBean = gson.fromJson(response, UserLoginBean.class);
//                        UserLoginBean userLoginBean = gson.fromJson(response, new TypeToken<ArrayList<UserLoginBean>>(){}.getType());
                    if(userLoginBean.isStatus()){
                        int identity= userLoginBean.getUser().getRemark();

                       MyApplication.identity= identity;
                       MyApplication.account=userLoginBean.getUser().getId()+"";
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
                    //用户名或密码错误
                    else{
                        ToastUtil.showToast(LoginActivity.this,"用户名或密码输入错误");
                    }
                }
            });
    }

    /**
     * 服务器连接不上
     *
     */
    private void showConectionDialog() {
        Log.i("TAG","进入alert设置");
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("提示");
        alert.setMessage("服务器错误，请检查服务器设置！");
        alert.setCancelable(false);
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.create();
        alert.show();
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
        }
    }

}
