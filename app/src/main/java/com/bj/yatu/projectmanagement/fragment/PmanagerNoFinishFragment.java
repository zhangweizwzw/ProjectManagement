package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.ProjectListAdapter;
import com.bj.yatu.projectmanagement.model.ProjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/5.
 */

public class PmanagerNoFinishFragment extends Fragment{
    private View view;
    private TextView text_center;
    private ListView project_lv;
    private List<ProjectBean> proList=new ArrayList<ProjectBean>();
    private ProjectListAdapter projectListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pmanager_nofinish, container, false);

        initView();
        initData();

        return view;
    }

    private void initData() {
        ProjectBean projectBean1=new ProjectBean();
        projectBean1.setProjectName("天水工业园区");
        projectBean1.setProjectRate("10");
        projectBean1.setProjectManager("王世勇");
        projectBean1.setProjectStarttime("2017-4-6");
        projectBean1.setProjectNowtime("2017-4-6");
        projectBean1.setProjectFinishtime("2017-4-6");
        projectBean1.setProjectPlan("暂无");
        projectBean1.setProjectFact("暂无");
        projectBean1.setProjectPeople("100");
        projectBean1.setProjectmoney("1000000");
        projectBean1.setProjectMinmoney("10000");
        projectBean1.setProjectproblem("多了去了");

        ProjectBean projectBean2=new ProjectBean();
        projectBean2.setProjectName("襄阳工业园区");
        projectBean2.setProjectRate("50");
        projectBean2.setProjectManager("李老大");
        projectBean2.setProjectStarttime("2017-4-6");
        projectBean2.setProjectNowtime("2017-4-6");
        projectBean2.setProjectFinishtime("2017-4-6");
        projectBean2.setProjectPlan("暂无");
        projectBean2.setProjectFact("暂无");
        projectBean2.setProjectPeople("100");
        projectBean2.setProjectmoney("1000000");
        projectBean2.setProjectMinmoney("10000");
        projectBean2.setProjectproblem("多的很");

        proList.add(projectBean1);
        proList.add(projectBean2);

        projectListAdapter.notifyDataSetChanged();

    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("项目管理");

        project_lv= (ListView) view.findViewById(R.id.project_lv);
        projectListAdapter=new ProjectListAdapter(getActivity(),proList);
        project_lv.setAdapter(projectListAdapter);
    }
}
