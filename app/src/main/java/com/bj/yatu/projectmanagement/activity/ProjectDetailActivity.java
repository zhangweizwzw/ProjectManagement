package com.bj.yatu.projectmanagement.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
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
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.model.ResponsePanelBean;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="ProjectDetailActivity";
    private TextView text_center,projectname_tv,starttime_tv,hopeendtime_tv,projectmanager_tv;
    private ImageView image_left;
    private RelativeLayout rela2;
    private  NestFullListView plans_lv;
    private ProjectDetailBean projectDetailBean;
    boolean flaga=true;//问题
    boolean flagb=true;//答复
    boolean flagc=true;//计划名称
    boolean flagd=true;//节点名称
    private String planid;//计划id
    private String nodeid;//节点id
    private String projectid="";//项目id
    private int planpoistion=0;//问题poistion
    private int nodepoistion=0;//节点poistion
    private ImageView display_iv,image_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        initView();
        initData(0);
    }

    private void initView() {
        projectid=getIntent().getStringExtra("projectid");

        image_right= (ImageView)findViewById(R.id.image_right);
        image_right.setImageResource(R.mipmap.refresh);
        image_right.setOnClickListener(this);

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

        display_iv= (ImageView) findViewById(R.id.display_iv);
    }

    //查询项目详情
    private void initData(final int mint) {
        ProcessUtil.showProcess(this,"正在查询，请稍后...");
        projectDetailBean=new ProjectDetailBean();
        Log.i(TAG,"id="+projectid);
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findoneproject?id="+projectid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(ProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        ProcessUtil.dismiss();
                        Log.i(TAG,"response="+response);
                        Gson gson=new Gson();
                        projectDetailBean=gson.fromJson(response, ProjectDetailBean.class);
                        if(projectDetailBean.isStatus()){
                            if(mint==0){
                                setData();
                            }else{
                                plans_lv.updateUI();
                            }
                        }
                    }
                });
    }

    //填充listview
    private void setData() {
        projectname_tv.setText(projectDetailBean.getProject().getProject_name());
        starttime_tv.setText(projectDetailBean.getProject().getProject_begin_time());
        hopeendtime_tv.setText(projectDetailBean.getProject().getProject_end_time());
        projectmanager_tv.setText(projectDetailBean.getProject().getProject_fzr());

        plans_lv.setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean>(R.layout.projectplansitem_layout, projectDetailBean.getProject().getProjectplans()) {
            @Override
            public void onBind(final int pos1, final ProjectDetailBean.ProjectBean.ProjectplansBean plans, final NestFullViewHolder holder) {
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
                        ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.display_iv1)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                        ((ImageView)holder.getView(R.id.display_iv1)).setImageResource(R.mipmap.jia);
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
                            ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.display_iv1)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                            ((ImageView)holder.getView(R.id.display_iv1)).setImageResource(R.mipmap.jia);
                            ((LinearLayout)holder.getView(R.id.detail_rela)).setVisibility(View.GONE);
                        }else{
                            ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.display_iv1)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                            ((ImageView)holder.getView(R.id.display_iv1)).setImageResource(R.mipmap.jian);
                            ((LinearLayout)holder.getView(R.id.detail_rela)).setVisibility(View.VISIBLE);
                        }
                    }
                });

                if(MyApplication.isprojectfinish){
                    ((TextView)holder.getView(R.id.addpanel)).setVisibility(View.GONE);
                }
               //添加节点
                ((TextView)holder.getView(R.id.addpanel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //节点总占比
                        Double prodouble=0.0;
                        for (int i=0;i<projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().size();i++){
                            prodouble=prodouble+projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().get(i).getNode_proportion();
                        }

                        Log.i(TAG,"prodouble="+prodouble);

                        //上一个计划是否完成
                        Double ious=0.0;
                        Log.i(TAG,"pos1"+pos1);
                        if(pos1!=0){
                            for (int i=0;i<projectDetailBean.getProject().getProjectplans().get(pos1-1).getNodes().size();i++){
                                ious=ious+projectDetailBean.getProject().getProjectplans().get(pos1-1).getNodes().get(i).getNode_proportion();
                            }
                            if(ious<100){
                                ToastUtil.showToast(ProjectDetailActivity.this,"上一个计划没完成，暂时不能添加节点！");
                            }else{
                                planpoistion=pos1;
                                planid=plans.getId()+"";
                                Intent intent=new Intent();
                                intent.setClass(ProjectDetailActivity.this,AddPlanActivity.class);
                                intent.putExtra("planstarttime",plans.getPlan_begin_time());
                                intent.putExtra("prodouble",prodouble);
                                startActivityForResult(intent,1);
                            }
                        }else{
                            planpoistion=pos1;
                            planid=plans.getId()+"";
                            Intent intent=new Intent();
                            intent.setClass(ProjectDetailActivity.this,AddPlanActivity.class);
                            intent.putExtra("planstarttime",plans.getPlan_begin_time());
                            intent.putExtra("prodouble",prodouble);
                            startActivityForResult(intent,1);
                        }
                    }
                });

                ((NestFullListView)holder.getView(R.id.panel_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean>(R.layout.projectpanelitem_layout, projectDetailBean.getProject().getProjectplans().get(pos1).getNodes()) {
                    @Override
                    public void onBind(final int pos2, final ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean nodes, final NestFullViewHolder holder) {
                        holder.setText(R.id.panelname_et,nodes.getNode_name());
                        holder.setText(R.id.finishsign_et,nodes.getNode_complete_flat());
                        holder.setText(R.id.endtime_et,nodes.getNode_end_time());
                        holder.setText(R.id.panelper_et,nodes.getNode_proportion()+"");
                        holder.setText(R.id.peoplecost_et,nodes.getNode_labor_cost()+"");
                        holder.setText(R.id.extras_et,nodes.getNode_extras_cost()+"");
                        holder.setText(R.id.beizhu_et,nodes.getNode_complete_flat());

                        if(MyApplication.isprojectfinish){
                            ((TextView)holder.getView(R.id.addquestion)).setVisibility(View.GONE);
                        }

                        ((TextView)holder.getView(R.id.addquestion)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                nodeid=nodes.getId()+"";
                                planpoistion=pos1;
                                nodepoistion=pos2;
                                Intent intent=new Intent();
                                intent.putExtra("ctype","提问");
                                intent.setClass(ProjectDetailActivity.this,AddQuestionandAnswerActivity.class);
                                startActivityForResult(intent,3);
                            }
                        });

                        //显示与隐藏项目详情
                        ((RelativeLayout)holder.getView(R.id.rela1)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //项目名称过长的时候显示与隐藏
                                if (flagd){
                                    flagd=false;
                                    ((TextView)holder.getView(R.id.panelname_et)).setEllipsize(null);//文字展开
                                    ((TextView)holder.getView(R.id.panelname_et)).setSingleLine(flagd);

                                }else {
                                    flagd=true;
                                    ((TextView)holder.getView(R.id.panelname_et)).setEllipsize(TextUtils.TruncateAt.END);//收缩
                                    ((TextView)holder.getView(R.id.panelname_et)).setSingleLine(flagd);
                                }

                                //计划详情的显示与隐藏
                                if(((LinearLayout)holder.getView(R.id.jdisplay)).getVisibility()==View.VISIBLE){
                                    ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.display_iv2)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                                    ((ImageView)holder.getView(R.id.display_iv2)).setImageResource(R.mipmap.jia);
                                    ((LinearLayout)holder.getView(R.id.jdisplay)).setVisibility(View.GONE);
                                }else{
                                    ObjectAnimator.ofFloat(((ImageView)holder.getView(R.id.display_iv2)), "rotationX", 0.0F, 180.0F).setDuration(500).start();
                                    ((ImageView)holder.getView(R.id.display_iv2)).setImageResource(R.mipmap.jian);
                                    ((LinearLayout)holder.getView(R.id.jdisplay)).setVisibility(View.VISIBLE);
                                }
                            }
                        });


                     ((LinearLayout)holder.getView(R.id.jdisplay)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            Log.i(TAG,"pos1="+pos1);
                            Log.i(TAG,"pos2="+pos2);

                            if(projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().get(pos2).getQuestions().size()==0 ){
                                ToastUtil.showToast(ProjectDetailActivity.this,"暂无问题");
                            }else{
                                MyApplication.qestionlist=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>();
                                MyApplication.qestionlist.addAll(projectDetailBean.getProject().getProjectplans().get(pos1).getNodes().get(pos2).getQuestions());
                                startActivity(new Intent(ProjectDetailActivity.this,QuestionListActivity.class));
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
        //添加节点内容
        if(requestCode==1&&resultCode==2){
            String panelname=data.getStringExtra("panelname").toString();
            String finishsign=data.getStringExtra("finishsign").toString();
            String endtime=data.getStringExtra("endtime").toString();
            String pancelper=data.getStringExtra("pancelper").toString();
            String peoplecost=data.getStringExtra("peoplecost").toString();
            String extras=data.getStringExtra("extras").toString();
            String panelstarttime=data.getStringExtra("panelstarttime").toString();

            goCreate(panelname,panelstarttime,endtime,pancelper,peoplecost,extras,finishsign);
            //提问内容
        }else if(requestCode==3&&resultCode==3){
            String iscontent=data.getStringExtra("iscontent").toString();
            if("1".equals(iscontent)){
                String content=data.getStringExtra("content").toString();
                Log.i(TAG,"提问===>"+content);
                goQusetion(content);
            }
        }
    }

    /**
     * 添加问题
     * @param content
     */
    private void goQusetion(final String content) {
        ProcessUtil.showProcess(this,"正在添加，请稍后...");
        Log.i(TAG,"nodeid=>"+nodeid);
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"addQuestion")
                .addParams("node_id",nodeid)
                .addParams("node_question",content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(ProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        ProcessUtil.dismiss();
                        Log.i(TAG,"创建问题response=="+response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response,StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(ProjectDetailActivity.this,"创建问题成功！");

                            ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean qBean=new ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean();
                            qBean.setNode_question(content);
                            qBean.setQuestiondate(Dateutil.getTodayDate());

                            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().get(nodepoistion).getQuestions().add(qBean);
                            plans_lv.updateUI();
                        }
                    }
                });
    }


    /**
     * 添加节点
     * @param panelname
     * @param panelstarttime
     * @param endtime
     * @param pancelper
     * @param peoplecost
     * @param extras
     * @param finishsign
     */
    private void goCreate(final String panelname,final String panelstarttime,final String endtime,final String pancelper,final String peoplecost,final String extras,final String finishsign) {
        ProcessUtil.showProcess(this,"正在添加，请稍后...");
        Log.i(TAG,"planid===>"+planid);
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"addNode")
                .addParams("plan_id",planid)
                .addParams("node_name",panelname)
                .addParams("node_proportion",pancelper)
                .addParams("node_extras_cost",extras)
                .addParams("node_labor_cost",peoplecost)
                .addParams("node_complete_flat",finishsign)
                .addParams("node_end_time",endtime)
                .addParams("node_begin_time",panelstarttime)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(ProjectDetailActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        ProcessUtil.dismiss();
                        Log.i(TAG,"添加节点response=="+response);
                        Gson gson=new Gson();
                        ResponsePanelBean responsePanelBean=gson.fromJson(response,ResponsePanelBean.class);
                        if(responsePanelBean.isStatus()){
                            ToastUtil.showToast(ProjectDetailActivity.this,"创建节点成功！");

                            ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean nBean=new ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean();
                            nBean.setId(responsePanelBean.getId());
                            nBean.setNode_name(panelname);
                            nBean.setNode_proportion(Double.valueOf(pancelper).doubleValue());
                            nBean.setNode_extras_cost(Double.valueOf(extras).doubleValue());
                            nBean.setNode_labor_cost(Double.valueOf(peoplecost).doubleValue());
                            nBean.setNode_complete_flat(finishsign);
                            nBean.setNode_end_time(endtime);
                            nBean.setNode_begin_time(panelstarttime);

                            List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> qlist=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>();
                            nBean.setQuestions(qlist);

                            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().add(nBean);
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
            case R.id.image_right:
                initData(1);
                break;
            case R.id.rela2:
                if(projectDetailBean.getProject().getProjectplans().size()==0){
                    ToastUtil.showToast(this,"暂无计划！");
                }else{
                    if(plans_lv.getVisibility()==View.VISIBLE){
                        ObjectAnimator.ofFloat(display_iv, "rotationX", 0.0F, 180.0F).setDuration(500).start();
                        display_iv.setImageResource(R.mipmap.jia);
                        plans_lv.setVisibility(View.GONE);
                    }else{
                        ObjectAnimator.ofFloat(display_iv, "rotationX", 0.0F, 180.0F).setDuration(500).start();
                        plans_lv.setVisibility(View.VISIBLE);
                        display_iv.setImageResource(R.mipmap.jian);
                    }
                }
                break;
        }
    }
}
