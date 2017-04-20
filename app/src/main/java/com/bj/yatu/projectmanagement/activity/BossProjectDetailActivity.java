package com.bj.yatu.projectmanagement.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.AddPanelBean;
import com.bj.yatu.projectmanagement.model.MessageEvent;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.model.StatusBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;
import com.bj.yatu.projectmanagement.utils.ProcessUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.NestFullListView;
import com.bj.yatu.projectmanagement.widget.NestFullListViewAdapter;
import com.bj.yatu.projectmanagement.widget.NestFullViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class BossProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="ProjectDetailActivity";
    private TextView text_center,projectname_tv,starttime_tv,hopeendtime_tv,projectmanager_tv,addplan;
    private ImageView image_left;
    private RelativeLayout rela2;
    private  NestFullListView plans_lv;
    private ProjectDetailBean projectDetailBean=new ProjectDetailBean();
    private List<AddPanelBean.ProjectplansBean> panelList=new ArrayList<AddPanelBean.ProjectplansBean>();
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    boolean flaga=true;//问题
    boolean flagb=true;//答复
    boolean flagc=true;//计划
    private String questionid;//问题id
    private String projectid;//项目id
    private int planpoistion=0;//问题poistion
    private int nodepoistion=0;//节点poistion
    private ImageView denote_project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_project_detail);

        initView();
        initData();
    }


    private void initView() {
        projectid=getIntent().getStringExtra("projectid");

        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("项目详情");
        image_left= (ImageView) findViewById(R.id.image_left);
        image_left.setImageResource(R.mipmap.title_back);
        image_left.setOnClickListener(this);
        rela2= (RelativeLayout) findViewById(R.id.rela2);
        rela2.setOnClickListener(this);

        plans_lv= (NestFullListView) findViewById(R.id.plans_lv);
        projectname_tv= (TextView) findViewById(R.id.projectname_tv);
        starttime_tv= (TextView) findViewById(R.id.starttime_tv);
        hopeendtime_tv= (TextView) findViewById(R.id.hopeendtime_tv);
        projectmanager_tv= (TextView) findViewById(R.id.projectmanager_tv);

        denote_project= (ImageView) findViewById(R.id.denote_project);

        addplan= (TextView) findViewById(R.id.addplan);//创建计划
        addplan.setOnClickListener(this);
        if(MyApplication.identity==0){
            addplan.setVisibility(View.GONE);
        }else{
            addplan.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        ProcessUtil.showProcess(this,"正在查询，请稍后...");
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findoneproject?id="+projectid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(BossProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        ProcessUtil.dismiss();
                        Gson gson=new Gson();
                        projectDetailBean=gson.fromJson(response, ProjectDetailBean.class);
                        if(projectDetailBean.isStatus()){
                            Log.i(TAG,projectDetailBean.isStatus()+"");
                            setData();
                        }
                    }
                });
    }

    private void setData() {
        projectname_tv.setText(projectDetailBean.getProject().getProject_name());
        starttime_tv.setText(projectDetailBean.getProject().getProject_begin_time());
        hopeendtime_tv.setText(projectDetailBean.getProject().getProject_end_time());
        projectmanager_tv.setText(projectDetailBean.getProject().getProject_fzr());


        plans_lv.setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean>(R.layout.managerprojectplansitem_layout, projectDetailBean.getProject().getProjectplans()) {
            @Override
            public void onBind(final int pos1, ProjectDetailBean.ProjectBean.ProjectplansBean plans, final NestFullViewHolder holder) {
                holder.setText(R.id.planname_et,plans.getPlan_name());
                holder.setText(R.id.finishsign_et,plans.getPlan_complete_flat());
                holder.setText(R.id.endtime_et,plans.getPlan_end_time());
                holder.setText(R.id.starttime_et,plans.getPlan_begin_time());
                holder.setText(R.id.planper_et,plans.getPlan_proportion()+"");
                holder.setText(R.id.peoplecost_et,plans.getPlan_labor_cost()+"");
                holder.setText(R.id.extras_et,plans.getPlan_extras_cost()+"");

                ((LinearLayout) holder.getView(R.id.close)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((LinearLayout)holder.getView(R.id.detail_rela)).setVisibility(View.GONE);
                    }
                });

                //显示与隐藏项目详情
                ((RelativeLayout)holder.getView(R.id.showdetail_rela)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //项目名称过长的时候显示与隐藏
                        if (flagc){
                            flagc=false;
                            ((TextView)holder.getView(R.id.planname_et)).setEllipsize(null);//文字展开
                            ((TextView)holder.getView(R.id.planname_et)).setSingleLine(flagc);

                        }else {
                            flagc=true;
                            ((TextView)holder.getView(R.id.planname_et)).setEllipsize(TextUtils.TruncateAt.END);//收缩
                            ((TextView)holder.getView(R.id.planname_et)).setSingleLine(flagc);
                        }

                        //计划详情的显示与隐藏
                        if(((LinearLayout)holder.getView(R.id.detail_rela)).getVisibility()==View.VISIBLE){
                            ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.denote_project)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                            ((ImageView)holder.getView(R.id.denote_project)).setImageResource(R.mipmap.you);
                            ((LinearLayout)holder.getView(R.id.detail_rela)).setVisibility(View.GONE);
                        }else{
                            ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.denote_project)), "rotationX", 0.0F, 360.0F).setDuration(500).start();
                            ((ImageView)holder.getView(R.id.denote_project)).setImageResource(R.mipmap.xia);
                            ((LinearLayout)holder.getView(R.id.detail_rela)).setVisibility(View.VISIBLE);
                        }
                    }
                });

                ((NestFullListView)holder.getView(R.id.panel_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean>(R.layout.managerprojectpanelitem_layout, projectDetailBean.getProject().getProjectplans().get(pos1).getNodes()) {
                    @Override
                    public void onBind(final int pos2, ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean nodes, final NestFullViewHolder holder) {
                        holder.setText(R.id.panelname_et,nodes.getNode_name());
                        holder.setText(R.id.finishsign_et,nodes.getNode_complete_flat());
                        holder.setText(R.id.endtime_et,nodes.getNode_end_time());
                        holder.setText(R.id.panelper_et,nodes.getNode_proportion()+"");
                        holder.setText(R.id.peoplecost_et,nodes.getNode_labor_cost()+"");
                        holder.setText(R.id.extras_et,nodes.getNode_extras_cost()+"");
                        holder.setText(R.id.starttime_et,nodes.getNode_begin_time());

                        ((LinearLayout)holder.getView(R.id.showquestion)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i(TAG,"pos1="+pos1);
                                Log.i(TAG,"pos2="+pos2);

                                planpoistion=pos1;
                                nodepoistion=pos2;

                                if(projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().get(pos2).getQuestions().size()==0 ){
                                    ToastUtil.showToast(BossProjectDetailActivity.this,"暂无问题");
                                }else{
                                    MyApplication.qestionlist=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>();
                                    MyApplication.qestionlist.addAll(projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().get(pos2).getQuestions());
                                    startActivity(new Intent(BossProjectDetailActivity.this,BossQuestionListActivity.class));
                                }
                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==1){
            String panelname=data.getStringExtra("panelname").toString();
            String finishsign=data.getStringExtra("finishsign").toString();
            String endtime=data.getStringExtra("endtime").toString();
            String pancelper=data.getStringExtra("pancelper").toString();
            String peoplecost=data.getStringExtra("peoplecost").toString();
            String extras=data.getStringExtra("extras").toString();
            String panelstarttime=data.getStringExtra("panelstarttime").toString();

            goCreate(panelname,panelstarttime,endtime,pancelper,peoplecost,extras,finishsign);
        }
    }



    private void goCreate(final String panelname,final String panelstarttime,final String endtime,final String pancelper,final String peoplecost,final String extras,final String finishsign) {
        ProcessUtil.showProcess(this,"正在添加，请稍后...");
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"addPlan")
                .addParams("project_id",projectDetailBean.getProject().getId()+"")
                .addParams("plan_name",panelname)
                .addParams("plan_proportion",pancelper)
                .addParams("plan_extras_cost",extras)
                .addParams("plan_labor_cost",peoplecost)
                .addParams("plan_complete_flat",finishsign)
                .addParams("plan_end_time",endtime)
                .addParams("plan_begin_time",panelstarttime)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(BossProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        ProcessUtil.dismiss();
                        Log.i(TAG,response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response,StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(BossProjectDetailActivity.this,"添加计划成功！");

                            ProjectDetailBean.ProjectBean.ProjectplansBean pBean=new ProjectDetailBean.ProjectBean.ProjectplansBean();
                            pBean.setPlan_name(panelname);
                            pBean.setPlan_create_time(Dateutil.getTodayDate());
                            pBean.setPlan_begin_time(panelstarttime);
                            pBean.setPlan_end_time(endtime);
                            pBean.setPlan_proportion(Double.valueOf(pancelper).doubleValue());
                            pBean.setPlan_labor_cost(Double.valueOf(peoplecost).doubleValue());
                            pBean.setPlan_extras_cost(Double.valueOf(extras).doubleValue());
                            pBean.setPlan_complete_flat(finishsign);

                            List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean> nList=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean>();
                            pBean.setNodes(nList);

                            projectDetailBean.getProject().getProjectplans().add(pBean);
                            plans_lv.updateUI();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_left:
                finish();
                break;
            case R.id.rela2:
                if(projectDetailBean.getProject().getProjectplans().size()==0){
                    ToastUtil.showToast(this,"暂无计划！");
                }else{
                    if(plans_lv.getVisibility()==View.VISIBLE){
                        ObjectAnimator.ofFloat(denote_project, "rotationX", 0.0F, 180.0F).setDuration(500).start();
                        plans_lv.setVisibility(View.GONE);
                        denote_project.setImageResource(R.mipmap.you);
                    }else{
                        ObjectAnimator.ofFloat(denote_project, "rotationX", 0.0F, 360.0F).setDuration(500).start();
                        plans_lv.setVisibility(View.VISIBLE);
                        denote_project.setImageResource(R.mipmap.xia);
                    }
                }
                break;
            case R.id.addplan:
                //上一个计划结束时间
                String firstplanendtime="";
                if(projectDetailBean.getProject().getProjectplans().size()!=0){
                    firstplanendtime=projectDetailBean.getProject().getProjectplans().get(projectDetailBean.getProject().getProjectplans().size()-1).getPlan_end_time();
                }


                //计划总占比
                Double planpro=0.0;
                for (int i=0;i<projectDetailBean.getProject().getProjectplans().size();i++){
                    planpro=planpro+projectDetailBean.getProject().getProjectplans().get(i).getPlan_proportion();
                }


                Intent intent=new Intent();
                intent.setClass(this,AddPanelActivity.class);
                intent.putExtra("prostarttime",projectDetailBean.getProject().getProject_begin_time());
                intent.putExtra("firstplanendtime",firstplanendtime);
                intent.putExtra("planpro",planpro);
                startActivityForResult(intent,2);
//                startActivityForResult(new Intent(this,AddPanelActivity.class),2);
                break;
        }
    }

    /**
     * 接收回复问题发过来的广播,把数据更新给项目详情
     * @param event
     */
    @Subscribe
    public void onEventMainThread(MessageEvent event){
        if("回复".equals(event.message)){
            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().get(nodepoistion).getQuestions().get(MyApplication.position).setAnswerdate(Dateutil.getTodayDate());
            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().get(nodepoistion).getQuestions().get(MyApplication.position).setNode_question_answer( MyApplication.questioncontent);
        }
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
