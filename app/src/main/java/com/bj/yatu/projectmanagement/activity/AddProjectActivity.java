package com.bj.yatu.projectmanagement.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.PanelListAdapter;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.AddPanelBean;
import com.bj.yatu.projectmanagement.model.ManagersBean;
import com.bj.yatu.projectmanagement.model.MessageEvent;
import com.bj.yatu.projectmanagement.model.StatusBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;
import com.bj.yatu.projectmanagement.utils.ProcessUtil;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.DatePickerDialog;
import com.bj.yatu.projectmanagement.widget.MaterialSpinner;
import com.bj.yatu.projectmanagement.widget.MyDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class AddProjectActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="AddProjectActivity";
    private TextView add_panel;
    private MaterialSpinner manager;
    private TextView text_center;
    private ImageView starttime_im,predicttime_im,image_left;
    private EditText start_time_et,predicttime_et,pro_name;
    private RecyclerView panel_rv;
    private List<AddPanelBean.ProjectplansBean> panelList=new ArrayList<AddPanelBean.ProjectplansBean>();
    private PanelListAdapter panelListAdapter;
    private TextView cancel_button,create_btn;
    private List<String> mlist = new ArrayList<String>();
    private ManagersBean managersBean;
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        initView();

        getManagers();
    }


    /**
     * 接收删除节点广播
     * @param event
     */
    @Subscribe
    public void onEventMainThread(MessageEvent event){

        showNetAlert(event);
    }

    private void showNetAlert(final MessageEvent event) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("提示");
        alert.setMessage("确定删除该节点？");
        alert.setCancelable(false);
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                panelList.remove(Integer.parseInt(event.message));
                panelListAdapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.create();
        alert.show();
    }


    private void initView() {
        image_left= (ImageView) findViewById(R.id.image_left);
        image_left.setImageResource(R.mipmap.title_back);
        image_left.setOnClickListener(this);
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("添加项目");
        add_panel= (TextView) findViewById(R.id.add_panel);
        add_panel.setOnClickListener(this);
        starttime_im= (ImageView) findViewById(R.id.starttime_im);
        starttime_im.setOnClickListener(this);
        predicttime_im= (ImageView) findViewById(R.id.predicttime_im);
        predicttime_im.setOnClickListener(this);
        start_time_et= (EditText) findViewById(R.id.start_time_et);//起始时间
        predicttime_et= (EditText) findViewById(R.id.predicttime_et);//预计完成时间
        pro_name= (EditText) findViewById(R.id.pro_name);//项目名称
        pro_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(pro_name.getWindowToken(), 0);
                }
            }
        });

        start_time_et.setOnClickListener(this);
        start_time_et.setInputType(InputType.TYPE_NULL);
        predicttime_et.setOnClickListener(this);
        predicttime_et.setInputType(InputType.TYPE_NULL);

        //项目节点列表
        panel_rv= (RecyclerView) findViewById(R.id.panel_rv);//节点列表
        panel_rv.setLayoutManager(new LinearLayoutManager(this));
        panelListAdapter=new PanelListAdapter(this,panelList);
        panel_rv.setAdapter(panelListAdapter);
        panel_rv.setItemAnimator(new DefaultItemAnimator());
        //这句就是添加我们自定义的分隔线
        panel_rv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

        //项目经理下拉框
        manager= (MaterialSpinner) findViewById(R.id.manager);

        cancel_button= (TextView) findViewById(R.id.cancel_button);
        create_btn= (TextView) findViewById(R.id.create_btn);
        cancel_button.setOnClickListener(this);
        create_btn.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            String panelname=data.getStringExtra("panelname").toString();
            String finishsign=data.getStringExtra("finishsign").toString();
            String endtime=data.getStringExtra("endtime").toString();
            String pancelper=data.getStringExtra("pancelper").toString();
            String peoplecost=data.getStringExtra("peoplecost").toString();
            String extras=data.getStringExtra("extras").toString();
            String panelstarttime=data.getStringExtra("panelstarttime").toString();

            AddPanelBean.ProjectplansBean panelBean=new AddPanelBean.ProjectplansBean();
            panelBean.setPlan_name(panelname);
            panelBean.setPlan_begin_time(panelstarttime);
            panelBean.setPlan_end_time(endtime);
            panelBean.setPlan_proportion(Double.valueOf(pancelper));
            panelBean.setPlan_labor_cost(Double.valueOf(peoplecost));
            panelBean.setPlan_extras_cost(Double.valueOf(extras));
            panelBean.setPlan_complete_flat(finishsign);

            panelList.add(panelBean);

            panelListAdapter.notifyDataSetChanged();
        }
    }

    public void getManagers() {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findManager")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                       ToastUtil.showToast(AddProjectActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,"项目经理列表："+response);
                        Gson gson=new Gson();
                        managersBean=gson.fromJson(response,ManagersBean.class);
                        String[] managers = new String[managersBean.getManager().size()];

                        if(managersBean.isStatus()){
                            for (int i=0;i<managersBean.getManager().size();i++){
                               Log.i(TAG,"AAAAAAAAAAAAAAAA"+i+"<-->"+managersBean.getManager().get(i).getUserName());
                                managers[i]=managersBean.getManager().get(i).getUserName();
                           }
                            mlist= Arrays.asList(managers);
                            manager.setItems(managers);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_left:
                goBack("0");
                break;
            case R.id.add_panel:
                //项目开始时间
                String pstarttime=start_time_et.getText().toString();

                //上一个计划结束时间
                String firstplanendtime="";
                if(panelList.size()>0){
                    firstplanendtime=panelList.get(panelList.size()-1).getPlan_end_time();
                }


                //计划相加不能大于100%
                Double planpro=0.0;
                for(int i=0;i<panelList.size();i++){
                    planpro=planpro+panelList.get(i).getPlan_proportion();
                }

                if(StringUtil.isEmpty(pstarttime)){
                    ToastUtil.showToast(this,"请输入项目开始时间！");
                }else{
                    Intent intent=new Intent();
                    intent.setClass(this,AddPanelActivity.class);
                    intent.putExtra("prostarttime",pstarttime);
                    intent.putExtra("firstplanendtime",firstplanendtime);
                    intent.putExtra("planpro",planpro);
                    startActivityForResult(intent,1);
                }
//                startActivityForResult(new Intent(this,AddPanelActivity.class),1);
                break;
            case R.id.starttime_im:
                showDialogDate(1);
                break;
            case R.id.predicttime_im:
                showDialogDate(2);
                break;
            case R.id.start_time_et:
                showDialogDate(1);
                break;
            case R.id.predicttime_et:
                showDialogDate(2);
                break;
            case R.id.cancel_button:
                goBack("0");
                break;
            case R.id.create_btn:
                String  proName=pro_name.getText().toString();
                String  startTime=start_time_et.getText().toString();
                String  predictTime=predicttime_et.getText().toString();
                String managerName=mlist.get(manager.getSelectedIndex());

                if(StringUtil.isEmpty(proName)){
                    ToastUtil.showToast(this,"项目名称不为空！");
                }else if(StringUtil.isEmpty(startTime)){
                    ToastUtil.showToast(this,"起始时间不为空！");
                }else if(!Dateutil.isValidDate(startTime)){
                    ToastUtil.showToast(this,"开始日期格式不正确！");
                }else if(StringUtil.isEmpty(predictTime)){
                    ToastUtil.showToast(this,"预计完成时间不为空！");
                }else if(!Dateutil.isValidDate(predictTime)){
                    ToastUtil.showToast(this,"预计结束日期格式不正确！");
                }else if(Dateutil.compare_date(predictTime,startTime)==1){
                    ToastUtil.showToast(this,"结束时间不能在开始时间之前！");
                }else if(StringUtil.isEmpty(managerName)){
                    ToastUtil.showToast(this,"项目经理不为空！");
                }else{
                    //获取项目经理id
                    String managerid="";
                    for (int i=0;i<managersBean.getManager().size();i++){
                        if(managersBean.getManager().get(i).getUserName().equals(managerName)){
                            managerid=managersBean.getManager().get(i).getId()+"";
                        }
                    }
                    Log.i(TAG,"managerName="+managerName);
                    Log.i(TAG,"managerid="+managerid);
                    goCreate(proName,startTime,predictTime,managerName,managerid);
                }
                break;
        }
    }

    private void goCreate(String proName,String startTime,String predictTime,String managerName,String managerid) {
        AddPanelBean addPanelBean=new AddPanelBean();
        addPanelBean.setId("");
        addPanelBean.setProject_name(proName);
        addPanelBean.setProject_sqrid(MyApplication.account);
        addPanelBean.setProject_fzr(managerName);
        addPanelBean.setProject_fzrid(managerid);
        addPanelBean.setProject_begin_time(startTime);
        addPanelBean.setProject_end_time(predictTime);
        addPanelBean.setProjectplans(panelList);

        Gson gson=new Gson();
        String jsonstr=gson.toJson(addPanelBean);
        Log.i(TAG,"jsonstr=="+jsonstr);

        OkHttpClient client = new OkHttpClient();
        RequestBody body=RequestBody.create(JSON,jsonstr);


        ProcessUtil.showProcess(this,"正在添加，请稍后...");
        OkHttpUtils
                .put()
                .url(RequstUrls.REQUEST_URL+"saveProject")
                .requestBody(body)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(AddProjectActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        ProcessUtil.dismiss();
                        Log.i(TAG,"response"+"<-->"+response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response, StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(AddProjectActivity.this,"添加成功！");

                            goBack("1");
                        }
                    }
                });

    }

    private void goBack(String isReflash) {
        Intent intent=new Intent();
        intent.putExtra("isReflash",isReflash);
        setResult(4,intent);
        finish();
    }

    public void showDialogDate(final int type) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);

        DatePickerDialog dialog = builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String result=dates[0] + "-" + dates[1] + "-" + dates[2];
                if(type==1){
                    start_time_et.setText(result);
                }else{
                    predicttime_et.setText(result);
                }
            }
        }).create();

        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
