package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.ProjectListAdapter;
import com.bj.yatu.projectmanagement.common.MyProgress;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.ProjectsBean;
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

/**
 * Created by admin on 2017/4/5.
 */

public class PmanagerNoFinishFragment extends Fragment{
    private final String TAG="PmanagerNoFinishFragment";
    private View view;
    private TextView text_center;
    private NestFullListView project_lv;
    private List<ProjectsBean.ProjectBean> proList=new ArrayList<ProjectsBean.ProjectBean>();
    private ProjectListAdapter projectListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pmanager_nofinish, container, false);

        initView();
//        initData();

        return view;
    }

    private void initData() {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findProjectList?project_isfinish=0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ToastUtil.showToast(getActivity(),"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,"response"+response);
                        Gson gson=new Gson();
                        ProjectsBean projectsBean=gson.fromJson(response,ProjectsBean.class);
                        if(projectsBean.isStatus()){
                            proList.addAll(projectsBean.getProject());
                            setData();
                        }
                    }
                });
//        ProjectBean projectBean1=new ProjectBean();
//        projectBean1.setProjectName("天水工业园区");
//        projectBean1.setProjectRate("10");
//        projectBean1.setProjectManager("王世勇");
//        projectBean1.setProjectStarttime("2017-4-6");
//        projectBean1.setProjectNowtime("2017-4-6");
//        projectBean1.setProjectFinishtime("2017-4-6");
//        projectBean1.setProjectPlan("暂无");
//        projectBean1.setProjectFact("暂无");
//        projectBean1.setProjectPeople("100");
//        projectBean1.setProjectmpBeany("1000000");
//        projectBean1.setProjectMinmpBeany("10000");
//        projectBean1.setProjectproblem("多了去了");
//
//        ProjectBean projectBean2=new ProjectBean();
//        projectBean2.setProjectName("襄阳工业园区");
//        projectBean2.setProjectRate("50");
//        projectBean2.setProjectManager("李老大");
//        projectBean2.setProjectStarttime("2017-4-6");
//        projectBean2.setProjectNowtime("2017-4-6");
//        projectBean2.setProjectFinishtime("2017-4-6");
//        projectBean2.setProjectPlan("暂无");
//        projectBean2.setProjectFact("暂无");
//        projectBean2.setProjectPeople("100");
//        projectBean2.setProjectmpBeany("1000000");
//        projectBean2.setProjectMinmpBeany("10000");
//        projectBean2.setProjectproblem("多的很");
//
//        proList.add(projectBean1);
//        proList.add(projectBean2);
//
//        projectListAdapter.notifyDataSetChanged();

    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("项目管理");


//        project_lv= (ListView) view.findViewById(R.id.project_lv);
//        projectListAdapter=new ProjectListAdapter(getActivity(),proList);
//        project_lv.setAdapter(projectListAdapter);
    }

    private void setData() {
        project_lv= (NestFullListView) view.findViewById(R.id.project_lv);
        project_lv.setAdapter(new NestFullListViewAdapter<ProjectsBean.ProjectBean>(R.layout.projectlist_item,proList) {
            @Override
            public void onBind(int pos, ProjectsBean.ProjectBean pBean, NestFullViewHolder holder) {
                holder.setText(R.id.projectname_tv, pBean.getProject_name());
                holder.setText(R.id.projectManager_tv, pBean.getProject_fzr());
                holder.setText(R.id.starttitme_tv, pBean.getProject_begin_time());
                holder.setText(R.id.nowtime_tv, "2002-2-2");
                holder.setText(R.id.expectfinishtime_tv, pBean.getProject_end_time());
                holder.setText(R.id.expect_tv, pBean.getProject_plan());
                holder.setText(R.id.fact_tv, pBean.getProject_fact());
                holder.setText(R.id.people_tv, pBean.getTotalpersoncost()+"");
                holder.setText(R.id.cost_tv, pBean.getTotalextracost()+"");
                holder.setText(R.id.costmin_tv, pBean.getTotalcost()+"");
                ((MyProgress)holder.getView(R.id.projectrate_pro)).setProgress((int)pBean.getTotalpercent());
            }
        });
    }
}
