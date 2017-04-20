package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.adapters.BossQuestionListAdapter;
import com.bj.yatu.projectmanagement.adapters.QuestionListAdapter;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.RequstUrls;
import com.bj.yatu.projectmanagement.model.MessageEvent;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.model.StatusBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;
import com.bj.yatu.projectmanagement.utils.ProcessUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class BossQuestionListActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="BossQuestionListActivity";
    private TextView text_center;
    private ImageView image_left;
    private ListView qlist_lv;
    private BossQuestionListAdapter qadapter;
    private RelativeLayout larage_rela,small_rela;
    private List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> qlist=new ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_question_list);

        initView();
    }
    private void initView() {
        larage_rela= (RelativeLayout) findViewById(R.id.larage_rela);
        larage_rela.setOnClickListener(this);
        small_rela= (RelativeLayout) findViewById(R.id.small_rela);
        small_rela.setOnClickListener(this);

        qlist_lv= (ListView) findViewById(R.id.qlist_lv);

        qlist.addAll(MyApplication.qestionlist);
        qadapter=new BossQuestionListAdapter(this,qlist);
        qlist_lv.setAdapter(qadapter);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==3&&resultCode==3){
            String iscontent=data.getStringExtra("iscontent").toString();
            if("1".equals(iscontent)){
                String content=data.getStringExtra("content").toString();
                goQusetion(content);
            }
        }
    }

    private void goQusetion(final String content) {
        ProcessUtil.showProcess(this,"正在回复，请稍后...");
        Log.i(TAG,"questionid=>"+MyApplication.questionid);
        OkHttpUtils
                .post()
                .url(RequstUrls.REQUEST_URL+"answerQuestion")
                .addParams("id",MyApplication.questionid+"")
                .addParams("node_question_answer",content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ProcessUtil.dismiss();
                        ToastUtil.showToast(BossQuestionListActivity.this,"请求失败！请检查网络设置");
                    }

                    @Override
                    public void onResponse(String response){
                        ProcessUtil.dismiss();
                        Log.i(TAG,"fffffff=="+response);
                        Gson gson=new Gson();
                        StatusBean statusBean=gson.fromJson(response,StatusBean.class);
                        if(statusBean.isStatus()){
                            ToastUtil.showToast(BossQuestionListActivity.this,"回复成功！");

//                            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().get(nodepoistion).getQuestions().get(questionpoistion).setAnswerdate(Dateutil.getTodayDate());
//                            projectDetailBean.getProject().getProjectplans().get(planpoistion).getNodes().get(nodepoistion).getQuestions().get(questionpoistion).setNode_question_answer(content);
//                            plans_lv.updateUI();


                            MyApplication.questioncontent=content;
                            EventBus.getDefault().post(new MessageEvent("回复"));
                            qlist.get(MyApplication.position).setAnswerdate(Dateutil.getTodayDate());
                            qlist.get(MyApplication.position).setNode_question_answer(content);

                            qadapter.notifyDataSetChanged();
                        }
                    }
                });

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
