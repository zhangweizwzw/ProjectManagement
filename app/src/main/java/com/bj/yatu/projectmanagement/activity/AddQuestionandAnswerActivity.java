package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.utils.StringUtil;
import com.bj.yatu.projectmanagement.utils.ToastUtil;

public class AddQuestionandAnswerActivity extends BaseActivity implements View.OnClickListener {
    private TextView addtype_tv;
    private EditText content_et;
    private TextView cancel_bt,sure_bt;
    private RelativeLayout large_rela,small_rela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questionand_answer);

        initView();
    }

    private void initView() {
        addtype_tv= (TextView) findViewById(R.id.addtype_tv);
        content_et= (EditText) findViewById(R.id.content_et);
        cancel_bt= (TextView) findViewById(R.id.cancel_bt);
        sure_bt= (TextView) findViewById(R.id.sure_bt);
        cancel_bt.setOnClickListener(this);
        sure_bt.setOnClickListener(this);

        large_rela= (RelativeLayout) findViewById(R.id.large_rela);
        small_rela= (RelativeLayout) findViewById(R.id.small_rela);
        large_rela.setOnClickListener(this);
        small_rela.setOnClickListener(this);

        String ctype=getIntent().getStringExtra("ctype");
        if("提问".equals(ctype)){
            addtype_tv.setText("提问");
        }else{
            addtype_tv.setText("回复");
        }
    }

    private void goBack(String iscontent,String content) {
        Intent intent=new Intent();
        intent.putExtra("iscontent",iscontent);
        intent.putExtra("content",content);
        setResult(3,intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel_bt:
                goBack("0","");
                break;
            case R.id.sure_bt:
                String content=content_et.getText().toString().trim();
                if(StringUtil.isEmpty(content)){
                    ToastUtil.showToast(this,"请输入内容！");
                }else{
                    goBack("1",content);
                }
                break;
            case R.id.large_rela:
                finish();
                break;
            case R.id.small_rela:
                finish();
                break;
        }
    }
}
