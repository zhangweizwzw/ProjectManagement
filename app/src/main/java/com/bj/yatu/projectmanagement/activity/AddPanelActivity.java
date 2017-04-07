package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.DatePickerDialog;

public class AddPanelActivity extends BaseActivity implements View.OnClickListener {
    private ImageView endtime_im;
    private EditText endtime_et,panelname_et,finishsign_et,pancelper_et,peoplecost_et,extras_et;
    private Button setup;
    private RelativeLayout largerela,smallrela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_panel);

        initView();
    }

    private void initView() {
        endtime_im= (ImageView) findViewById(R.id.endtime_im);
        endtime_im.setOnClickListener(this);
        endtime_et= (EditText) findViewById(R.id.endtime_et);//完成日期
        panelname_et= (EditText) findViewById(R.id.panelname_et);//节点名称
        finishsign_et= (EditText) findViewById(R.id.finishsign_et);//完成标志
        pancelper_et= (EditText) findViewById(R.id.pancelper_et);//节点占比
        peoplecost_et= (EditText) findViewById(R.id.peoplecost_et);//人工成本
        extras_et= (EditText) findViewById(R.id.extras_et);//杂费
        setup= (Button) findViewById(R.id.setup);
        setup.setOnClickListener(this);
        //点击空白部分关掉activity
        largerela= (RelativeLayout) findViewById(R.id.largerela);
        largerela.setOnClickListener(this);
        smallrela= (RelativeLayout) findViewById(R.id.smallrela);
        smallrela.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.endtime_im:
                showDialogDate();
                break;
            case R.id.setup:
                String panelname=panelname_et.getText().toString().trim();
                String finishsign=finishsign_et.getText().toString().trim();
                String endtime=endtime_et.getText().toString().trim();
                String pancelper=pancelper_et.getText().toString().trim();
                String peoplecost=peoplecost_et.getText().toString().trim();
                String extras=extras_et.getText().toString().trim();
                if(StringUtil.isEmpty(panelname)){
                    ToastUtil.showToast(this,"请输入节点名称！");
                }else if(StringUtil.isEmpty(finishsign)){
                    ToastUtil.showToast(this,"请输入完成标志！");
                }else if(StringUtil.isEmpty(endtime)){
                    ToastUtil.showToast(this,"请输入完成日期！");
                }else if(StringUtil.isEmpty(pancelper)){
                    ToastUtil.showToast(this,"请输入节点占比！");
                }else if(StringUtil.isEmpty(peoplecost)){
                    ToastUtil.showToast(this,"请输入人工成本！");
                }else if(StringUtil.isEmpty(extras)){
                    ToastUtil.showToast(this,"请输入杂费！");
                }else{
                    goSetup(panelname,finishsign,endtime,pancelper,peoplecost,extras);
                }
                break;
            case R.id.largerela:
                finish();
                break;
            case R.id.smallrela:

                break;
        }
    }

    private void goSetup(String panelname,String finishsign,String endtime,String pancelper,String peoplecost,String extras) {
        Intent intent=new Intent();
        intent.putExtra("panelname",panelname);
        intent.putExtra("finishsign",finishsign);
        intent.putExtra("endtime",endtime);
        intent.putExtra("pancelper",pancelper);
        intent.putExtra("peoplecost",peoplecost);
        intent.putExtra("extras",extras);
        setResult(1,intent);
        finish();
    }

    public void showDialogDate() {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);

        DatePickerDialog dialog = builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String result=dates[0] + "-" + dates[1] + "-" + dates[2];
                endtime_et.setText(result);
            }
        }).create();

        dialog.show();
    }
}
