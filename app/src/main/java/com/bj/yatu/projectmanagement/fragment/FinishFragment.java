package com.bj.yatu.projectmanagement.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;

import org.w3c.dom.Text;

/**
 * Created by admin on 2017/4/5.
 */

public class FinishFragment extends Fragment{
    private View view;
    private TextView text_center;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finish, container, false);

        initView();

        return view;
    }

    private void initView() {
        text_center= (TextView) view.findViewById(R.id.text_center);
        text_center.setText("已完成");
    }
}
