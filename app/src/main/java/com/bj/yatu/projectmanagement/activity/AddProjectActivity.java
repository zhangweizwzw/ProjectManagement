package com.bj.yatu.projectmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.PanelListAdapter;
import com.bj.yatu.projectmanagement.model.MessageEvent;
import com.bj.yatu.projectmanagement.model.PanelBean;
import com.bj.yatu.projectmanagement.widget.DatePickerDialog;
import com.bj.yatu.projectmanagement.widget.MaterialSpinner;
import com.bj.yatu.projectmanagement.widget.MyDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class AddProjectActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="AddProjectActivity";
    private TextView add_panel;
    private static final String[] ANDROID_VERSIONS = {"王世勇", "哈哈哈", "3", "4", "5", "6"};
    private MaterialSpinner manager;
    private TextView text_center;
    private ImageView starttime_im,predicttime_im,image_left;
    private EditText start_time_et,predicttime_et,pro_name;
    private RecyclerView panel_rv;
    private List<PanelBean> panelList=new ArrayList<PanelBean>();
    private PanelListAdapter panelListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        initView();
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

        //项目经理列表
        panel_rv= (RecyclerView) findViewById(R.id.panel_rv);//节点列表
        panel_rv.setLayoutManager(new LinearLayoutManager(this));
        panelListAdapter=new PanelListAdapter(this,panelList);
        panel_rv.setAdapter(panelListAdapter);
        panel_rv.setItemAnimator(new DefaultItemAnimator());
        //这句就是添加我们自定义的分隔线
        panel_rv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));


        manager= (MaterialSpinner) findViewById(R.id.manager);//项目经理
        manager.setItems(ANDROID_VERSIONS);
        manager.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Log.i(TAG,"AAA"+item);
            }
        });
        manager.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Log.i(TAG,"没有选中什么");
            }
        });
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

            PanelBean panelBean=new PanelBean();
            panelBean.setPanelname(panelname);
            panelBean.setFinishsign(finishsign);
            panelBean.setEndtime(endtime);
            panelBean.setPancelper(pancelper);
            panelBean.setPeoplecost(peoplecost);
            panelBean.setExtras(extras);

            panelList.add(panelBean);
            panelListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_left:
               finish();
                break;
            case R.id.add_panel:
                startActivityForResult(new Intent(this,AddPanelActivity.class),1);
                break;
            case R.id.starttime_im:
                showDialogDate(1);
                break;
            case R.id.predicttime_im:
                showDialogDate(2);
                break;
        }
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
