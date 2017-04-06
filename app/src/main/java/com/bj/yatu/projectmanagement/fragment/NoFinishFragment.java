package com.bj.yatu.projectmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.activity.AddProjectActivity;

import org.w3c.dom.Text;

/**
 * Created by admin on 2017/4/5.
 */

public class NoFinishFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView addproject;
    private TextView text_center;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nofinish, container, false);

        initView();

        return view;
    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("未完成");

        addproject= (ImageView) view.findViewById(R.id.addproject);
        addproject.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addproject:
                startActivity(new Intent(getActivity(), AddProjectActivity.class));
                break;
        }
    }
}
