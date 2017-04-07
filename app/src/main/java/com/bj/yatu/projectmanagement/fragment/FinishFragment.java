package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.NofinishAdapter;
import com.bj.yatu.projectmanagement.common.Info;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/5.
 */

public class FinishFragment extends Fragment{
    private View view;
    private TextView text_center;

    private ListView listView;
    private List<Info> list;
    private NofinishAdapter nofinishAdapter;
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
        list=new ArrayList<>();
        Info info1=new Info("项目名称：一一一一一一一",40);
        Info info2=new Info("项目名称：二二二二二",80);
        Info info3=new Info("项目名称：三三三三三三",20);
        Info info4=new Info("项目名称：四四",50);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);

        nofinishAdapter=new NofinishAdapter(getActivity(),list);
        listView.setAdapter(nofinishAdapter);

//       点击展开隐藏内容
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nofinishAdapter.changeImageVisable(view,i);
            }
        });
    }
}
