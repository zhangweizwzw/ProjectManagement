package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.utils.Dateutil;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.DatePickerDialog;

public class AddPanelActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="AddPanelActivity";
    private ImageView endtime_im,starttime_im;
    private EditText endtime_et,panelname_et,finishsign_et,pancelper_et,peoplecost_et,extras_et,starttime_et;
    private Button setup;
    private RelativeLayout largerela,smallrela;
    private String prostarttime="";//项目开始时间
    private String firstplanendtime="";//上一个计划结束时间
    private Double planpro=0.0;//计划总占比

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_panel);

        initView();
    }

    private void initView() {
        //项目开始时间
        prostarttime=getIntent().getStringExtra("prostarttime");
        firstplanendtime=getIntent().getStringExtra("firstplanendtime");
        planpro=getIntent().getDoubleExtra("planpro",0.0);

        Log.i(TAG,"prostarttime="+prostarttime);

        endtime_im= (ImageView) findViewById(R.id.endtime_im);
        starttime_im= (ImageView) findViewById(R.id.starttime_im);
        starttime_im.setOnClickListener(this);
        endtime_im.setOnClickListener(this);
        starttime_et= (EditText) findViewById(R.id.starttime_et);//开始日期
        starttime_et.setOnClickListener(this);
        starttime_et.setInputType(InputType.TYPE_NULL);
        endtime_et= (EditText) findViewById(R.id.endtime_et);//完成日期
        endtime_et.setOnClickListener(this);
        endtime_et.setInputType(InputType.TYPE_NULL);
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
                showDialogDate(1);
                break;
            case R.id.starttime_im:
                showDialogDate(2);
                break;
            case R.id.starttime_et:
                showDialogDate(1);
                break;
            case R.id.endtime_et:
                showDialogDate(2);
                break;
            case R.id.setup:
                String panelname=panelname_et.getText().toString().trim();
                String finishsign=finishsign_et.getText().toString().trim();
                String endtime=endtime_et.getText().toString().trim();
                String pancelper=pancelper_et.getText().toString().trim();
                String peoplecost=peoplecost_et.getText().toString().trim();
                String extras=extras_et.getText().toString().trim();
                String starttime=starttime_et.getText().toString().trim();

                Double costa=0.0;
                if("".equals(pancelper)){
                    costa=0.0;
                }else{
                    costa=Double.valueOf(pancelper).doubleValue();
                }

                Double costplanpro=costa+planpro;



                if(StringUtil.isEmpty(panelname)){
                    ToastUtil.showToast(this,"请输入计划名称！");
                }else if(StringUtil.isEmpty(finishsign)){
                    ToastUtil.showToast(this,"请输入完成标志！");
                }else if(StringUtil.isEmpty(starttime)){
                    ToastUtil.showToast(this,"请输入开始日期！");
                }else if(StringUtil.isEmpty(endtime)){
                    ToastUtil.showToast(this,"请输入完成日期！");
                }else if(StringUtil.isEmpty(pancelper)){
                    ToastUtil.showToast(this,"请输入计划占比！");
                }else if(Double.valueOf(pancelper).doubleValue()>100){//节点占比小于等于100%
                    ToastUtil.showToast(this,"计划占比小于100%！");
                }else if(StringUtil.isEmpty(peoplecost)){
                    ToastUtil.showToast(this,"请输入人工成本！");
                }else if(StringUtil.isEmpty(extras)){
                    ToastUtil.showToast(this,"请输入杂费！");
                }else if(Dateutil.compare_date(starttime,prostarttime)==1){//计划的开始时间在项目的结束时间之后
                    Log.i(TAG,"prostarttime="+prostarttime);
                    Log.i(TAG,"starttime="+starttime);

                    ToastUtil.showToast(this,"计划开始时间需在项目开始时间之后！");
                }else if(costplanpro>100){
                    ToastUtil.showToast(this,"计划总占比不能大于100%！");
                }else{
                    if(!"".equals(firstplanendtime)){
                        if(Dateutil.compare_date1(starttime,firstplanendtime)==1){
                            ToastUtil.showToast(this,"计划开始时间不能在上一个计划结束时间之前！");
                        }else{
                            goSetup(panelname,finishsign,starttime,endtime,pancelper,peoplecost,extras);
                        }
                    }else{
                        goSetup(panelname,finishsign,starttime,endtime,pancelper,peoplecost,extras);
                    }
                }
                break;
            case R.id.largerela:
                finish();
                break;
            case R.id.smallrela:

                break;
        }
    }

    private void goSetup(String panelname,String finishsign,String panelstarttime,String endtime,String pancelper,String peoplecost,String extras) {
        Intent intent=new Intent();
        intent.putExtra("panelname",panelname);
        intent.putExtra("finishsign",finishsign);
        intent.putExtra("endtime",endtime);
        intent.putExtra("pancelper",pancelper);
        intent.putExtra("peoplecost",peoplecost);
        intent.putExtra("panelstarttime",panelstarttime);
        intent.putExtra("extras",extras);
        setResult(1,intent);
        finish();
    }

    public void showDialogDate(final int type) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);

        DatePickerDialog dialog = builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String result=dates[0] + "-" + dates[1] + "-" + dates[2];
                if(type==1){
                    endtime_et.setText(result);
                }else{
                    starttime_et.setText(result);
                }

            }
        }).create();

        dialog.show();
    }
}
