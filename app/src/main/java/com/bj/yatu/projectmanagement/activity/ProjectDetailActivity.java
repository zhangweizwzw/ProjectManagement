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
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.bj.yatu.projectmanagement.widget.NestFullListView;
import com.bj.yatu.projectmanagement.widget.NestFullListViewAdapter;
import com.bj.yatu.projectmanagement.widget.NestFullViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import okhttp3.Call;

public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="ProjectDetailActivity";
    private TextView text_center,projectname_tv,starttime_tv,hopeendtime_tv,projectmanager_tv;
    private ImageView image_left;
    private RelativeLayout rela2;
    private  NestFullListView plans_lv;
    private ProjectDetailBean projectDetailBean=new ProjectDetailBean();
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        initView();
        initData();
    }

    private void initData() {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findoneproject?id=1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ToastUtil.showToast(ProjectDetailActivity.this,"请求失败！请检查网络设置");
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
        projectmanager_tv.setText(projectDetailBean.getProject().getProject_name());


        plans_lv.setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean>(R.layout.projectplansitem_layout, projectDetailBean.getProject().getProjectplans()) {
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

                ((TextView)holder.getView(R.id.addpanel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(ProjectDetailActivity.this,"创建节点");

//                        Intent intent=new Intent();
//
//                        startActivityForResult(new Intent(ProjectDetailActivity.this,AddPanelActivity.class),2);
                    }
                });

                ((NestFullListView)holder.getView(R.id.panel_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean>(R.layout.projectpanelitem_layout, projectDetailBean.getProject().getProjectplans().get(pos).getNodes()) {
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

                        ((NestFullListView)holder.getView(R.id.question_lv)).setAdapter(new NestFullListViewAdapter<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>(R.layout.projectquestionitem_layout, projectDetailBean.getProject().getProjectplans().get(pos).getNodes().get(pos).getQuestions()) {
                            @Override
                            public void onBind(int pos, ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean question, final NestFullViewHolder holder) {
                                holder.setText(R.id.question_date,question.getQuestiondate());
                                holder.setText(R.id.question_tv,question.getNode_question());
                                holder.setText(R.id.answerquestion_tv,question.getNode_question_answer());


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

//        cstFullShowListView.setAdapter(new NestFullListViewAdapter<One>(R.layout.item_lv, ailist) {
//            @Override
//            public void onBind(int pos, One one, final NestFullViewHolder holder) {
//                holder.setText(R.id.tv, one.getName());
//
//                final NestFullListView nestFullListView=(NestFullListView)holder.getView(R.id.cstFullShowListView2);
//
//                ((LinearLayout)holder.getView(R.id.lin1)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(((NestFullListView)holder.getView(R.id.cstFullShowListView2)).getVisibility()==View.VISIBLE){
//                            ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setVisibility(View.GONE);
//                        }else{
//                            ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//
//                ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setAdapter(new NestFullListViewAdapter<Two>(R.layout.item_lv2, one.getLlist()) {
//                    @Override
//                    public void onBind(int pos,Two two,final NestFullViewHolder holder) {
//                        holder.setText(R.id.tv, two.getName());
//
//                        final NestFullListView cstFullShowListView3=(NestFullListView)holder.getView(R.id.cstFullShowListView3);
//
//                        ((LinearLayout)holder.getView(R.id.lin2)).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if(((NestFullListView)holder.getView(R.id.cstFullShowListView3)).getVisibility()==View.VISIBLE){
//                                    ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setVisibility(View.GONE);
//                                }else{
//                                    ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setVisibility(View.VISIBLE);
//                                }
//                            }
//                        });
//
//                        ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setAdapter(new NestFullListViewAdapter<Three>(R.layout.item_lv3, two.getTlist()) {
//                            @Override
//                            public void onBind(int pos, Three three,final NestFullViewHolder holder) {
//                                holder.setText(R.id.tv, three.getTime());
//                                holder.setText(R.id.question_tv, three.getQuestion());
//                                holder.setText(R.id.depict_tv, three.getDepict());
//
//                                ((LinearLayout)holder.getView(R.id.lin3)).setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        if( ((RelativeLayout)holder.getView(R.id.question_rela)).getVisibility()==View.VISIBLE){
//                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.GONE);
//                                        }else{
//                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.VISIBLE);
//                                        }
//                                    }
//                                });
//                            }
//                        });
//
//
//                    }
//                });
//
//            }
//        });


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
        }
    }
}
