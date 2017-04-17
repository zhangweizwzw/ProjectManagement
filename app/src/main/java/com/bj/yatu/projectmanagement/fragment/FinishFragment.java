package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.FinishAdapter;
import com.bj.yatu.projectmanagement.adapters.NofinishAdapter;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.ProjectsBean;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by admin on 2017/4/5.
 */

public class FinishFragment extends Fragment{
    private View view;
    private TextView text_center;
    private ListView listView;
    private List<ProjectsBean.ProjectBean> mlist;
    private List<ProjectsBean.ProjectBean> list;;
    private FinishAdapter finishAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finish, container, false);

        initView();

        return view;
    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("已完成");
        listView= (ListView) view.findViewById(R.id.finish_listview);
        setBean();

//       点击展开隐藏内容
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finishAdapter.changeImageVisable(view,i);
            }
        });
    }

    private void setBean() {
        OkHttpUtils.get()
                .url(RequstUrls.REQUEST_URL+"findprojectlist?id="+ MyApplication.account)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                ToastUtil.showToast(getActivity(),"请求失败！请检查网络设置");
            }

            @Override
            public void onResponse(String response) {
                Log.i("===NoFinishRespnse====",response);
                Gson gson=new Gson();
                ProjectsBean mbean=gson.fromJson(response,ProjectsBean.class);
                mlist=new ArrayList<ProjectsBean.ProjectBean>();
                list=new ArrayList<ProjectsBean.ProjectBean>();
                mlist=mbean.getProject();
                //将未完成与已完成的分开
                for (int i = 0; i < mlist.size(); i++) {
                    double a=mlist.get(i).getTotalpercent();//获取百分比
                    double b=100.0;
                    BigDecimal data1=new BigDecimal(a);
                    BigDecimal data2=new BigDecimal(b);
                    if (data1.compareTo(data2)==0){
                        list.add(mlist.get(i));
                    }
//                    if (mlist.get(i).isProject_isfinish()){
//                        list.add(mlist.get(i));
//                    }
                }
                finishAdapter=new FinishAdapter(getActivity(),list);
                listView.setAdapter(finishAdapter);
            }
        });
    }
}
