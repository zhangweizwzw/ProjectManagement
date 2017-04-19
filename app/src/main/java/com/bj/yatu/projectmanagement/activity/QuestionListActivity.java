package com.bj.yatu.projectmanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.QuestionListAdapter;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_center;
    private ImageView image_left;
    private ListView qlist_lv;
    private QuestionListAdapter qadapter;
    private RelativeLayout larage_rela,small_rela;
    private List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> qlist=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        initView();

    }

    private void initView() {
        larage_rela= (RelativeLayout) findViewById(R.id.larage_rela);
        larage_rela.setOnClickListener(this);
        small_rela= (RelativeLayout) findViewById(R.id.small_rela);
        small_rela.setOnClickListener(this);

        qlist_lv= (ListView) findViewById(R.id.qlist_lv);

        qlist.addAll(MyApplication.qestionlist);
        qadapter=new QuestionListAdapter(this,qlist);
        qlist_lv.setAdapter(qadapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.larage_rela:
                finish();
                break;
            case R.id.small_rela:

                break;
        }
    }
}
