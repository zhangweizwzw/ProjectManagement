package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.NofinishAdapter;
import com.bj.yatu.projectmanagement.common.Info;

import java.util.List;

/**
 * Created by admin on 2017/4/5.
 */

public class NoFinishFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView addproject;
    private ListView listView;
    private List<Info> list;
    private NofinishAdapter nofinishAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nofinish, container, false);

        initView();

        return view;
    }

    private void initView() {
        addproject= (ImageView) view.findViewById(R.id.addproject);
        addproject.setOnClickListener(this);
        listView= (ListView) view.findViewById(R.id.nofinish_listview);
        nofinishAdapter=new NofinishAdapter(getContext(),list);
        listView.setAdapter(nofinishAdapter);
        //点击展开隐藏内容
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addproject:

                break;
        }
    }
}
