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
    private ListView project_lv;
    private List<ProjectsBean.ProjectBean> proList=new ArrayList<ProjectsBean.ProjectBean>();
    private ProjectListAdapter projectListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pmanager_nofinish, container, false);

        initView();
        initData();

        return view;
    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("未完成");

        project_lv= (ListView) view.findViewById(R.id.project_lv);
        projectListAdapter=new ProjectListAdapter(false,getActivity(),proList);
        project_lv.setAdapter(projectListAdapter);
    }

    private void initData() {
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"findprojectlist?project_sqrid=1")
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
                        proList.addAll(projectsBean.getProject());
                        if(projectsBean.isStatus()){
                            for (int i=0;i<proList.size();i++){
                                if(proList.get(i).getTotalpercent()==100){
                                    proList.remove(i);
                                }
                            }
                            projectListAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
