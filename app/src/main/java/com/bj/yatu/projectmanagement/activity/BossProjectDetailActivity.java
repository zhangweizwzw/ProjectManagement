package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.AddPanelBean;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.model.StatusBean;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.NestFullListView;
import com.bj.yatu.projectmanagement.widget.NestFullListViewAdapter;
import com.bj.yatu.projectmanagement.widget.NestFullViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    boolean flag=true;
    private String questionid;//问题id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_project_detail);

        initView();
        initData();
    }


    private void initView() {
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


        addplan= (TextView) findViewById(R.id.addplan);//创建计划
        addplan.setOnClickListener(this);
        if(MyApplication.identity==0){
            addplan.setVisibility(View.GONE);
        }else{
            addplan.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        String projectid=getIntent().getStringExtra("projectid");//项目id

        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findoneproject?id=1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ToastUtil.showToast(BossProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
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
            public void onBind(int pos, ProjectDetailBean.ProjectBean.ProjectplansBean plans, final NestFullViewHolder holder) {
                holder.setText(R.id.planname_et,plans.getPlan_name());
                holder.setText(R.id.finishsign_et,plans.getPlan_complete_flat());
                holder.setText(R.id.endtime_et,plans.getPlan_end_time());
                holder.setText(R.id.planper_et,plans.getPlan_proportion()+"");
                holder.setText(R.id.peoplecost_et,plans.getPlan_labor_cost()+"");
                holder.setText(R.id.extras_et,plans.getPlan_extras_cost()+"");

                ((RelativeLayout)holder.getView(R.id.showpanle)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((NestFullListView)holder.getView(R.id.panel_lv)).getVisibility()==View.VISIBLE){
                            ((NestFullListView)holder.getView(R.id.panel_lv)).setVisibility(View.GONE);
                        }else{
                            ((NestFullListView)holder.getView(R.id.panel_lv)).setVisibility(View.VISIBLE);
                        }
                    }
                });

                ((NestFullListView)holder.getView(R.id.panel_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean>(R.layout.managerprojectpanelitem_layout, projectDetailBean.getProject().getProjectplans().get(pos).getNodes()) {
                    @Override
                    public void onBind(int pos, ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean nodes, final NestFullViewHolder holder) {
                        holder.setText(R.id.panelname_et,nodes.getNode_name());
                        holder.setText(R.id.finishsign_et,nodes.getNode_complete_flat());
                        holder.setText(R.id.endtime_et,nodes.getNode_end_time());
                        holder.setText(R.id.panelper_et,nodes.getNode_proportion()+"");
                        holder.setText(R.id.peoplecost_et,nodes.getNode_labor_cost()+"");
                        holder.setText(R.id.extras_et,nodes.getNode_extras_cost()+"");

                        ((RelativeLayout)holder.getView(R.id.showquestion)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(((NestFullListView)holder.getView(R.id.question_lv)).getVisibility()==View.VISIBLE){
                                    ((NestFullListView)holder.getView(R.id.question_lv)).setVisibility(View.GONE);
                                }else{
                                    ((NestFullListView)holder.getView(R.id.question_lv)).setVisibility(View.VISIBLE);
                                }
                            }
                        });

                        ((NestFullListView)holder.getView(R.id.question_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>(R.layout.managerprojectquestionitem_layout, projectDetailBean.getProject().getProjectplans().get(pos).getNodes().get(pos).getQuestions()) {
                            @Override
                            public void onBind(int pos,final ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean question, final NestFullViewHolder holder) {
                                holder.setText(R.id.question_date,question.getQuestiondate());
                                holder.setText(R.id.question_tv,question.getNode_question());
                                holder.setText(R.id.answerquestion_tv,question.getNode_question_answer());

                                if(MyApplication.identity==0){
                                    ((TextView)holder.getView(R.id.answerq)).setVisibility(View.GONE);
                                }

                                ((TextView)holder.getView(R.id.answerq)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        questionid=question.getId()+"";
                                        Intent intent=new Intent();
                                        intent.putExtra("ctype","答复");
                                        intent.setClass(BossProjectDetailActivity.this,AddQuestionandAnswerActivity.class);
                                        startActivityForResult(intent,3);
                                    }
                                });


                                ((TextView)holder.getView(R.id.question_tv)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (flag){
                                            flag=false;
                                            ((TextView)holder.getView(R.id.question_tv)).setEllipsize(null);//文字展开
                                            ((TextView)holder.getView(R.id.question_tv)).setSingleLine(flag);

                                        }else {
                                            flag=true;
                                            ((TextView)holder.getView(R.id.question_tv)).setEllipsize(TextUtils.TruncateAt.END);//收缩
                                            ((TextView)holder.getView(R.id.question_tv)).setSingleLine(flag);
                                        }
                                    }
                                });

                                ((RelativeLayout)holder.getView(R.id.showquestion)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(((RelativeLayout)holder.getView(R.id.question_rela)).getVisibility()==View.VISIBLE){
                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.GONE);
                                        }else{
                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
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
        }else if(requestCode==3&&resultCode==3){
            String iscontent=data.getStringExtra("iscontent").toString();
            if("1".equals(iscontent)){
                String content=data.getStringExtra("content").toString();
                Log.i(TAG,"答复===>"+content);
                goQusetion(content);
            }
        }
    }

    private void goQusetion(String content) {
        Log.i(TAG,"questionid=>"+questionid);
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"answerQuestion")
                .addParams("id",questionid)
                .addParams("node_question_answer",content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ToastUtil.showToast(BossProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        Log.i(TAG,"fffffff=="+response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response,StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(BossProjectDetailActivity.this,"回复成功！");
                        }
                    }
                });

    }

    private void goCreate(String panelname,String panelstarttime,String endtime,String pancelper,String peoplecost,String extras,String finishsign) {
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
                        ToastUtil.showToast(BossProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        Log.i(TAG,response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response,StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(BossProjectDetailActivity.this,"添加计划成功！");
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
                if(plans_lv.getVisibility()==View.VISIBLE){
                    plans_lv.setVisibility(View.GONE);
                }else{
                    plans_lv.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.addplan:
                startActivityForResult(new Intent(this,AddPanelActivity.class),2);
                break;
        }
    }
}
