package com.bj.yatu.projectmanagement.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.model.PanelBean;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_center;
    private ImageView image_left;
    private RelativeLayout rela2;
    private ListView panel_lv;
    private List<PanelBean> panelList=new ArrayList<PanelBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        initView();
    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("项目详情");
        image_left= (ImageView) findViewById(R.id.image_left);
        image_left.setImageResource(R.mipmap.title_back);
        image_left.setOnClickListener(this);
        rela2= (RelativeLayout) findViewById(R.id.rela2);
        rela2.setOnClickListener(this);
        panel_lv= (ListView) findViewById(R.id.panel_lv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_left:
                finish();
                break;
            case R.id.rela2:
                if(panel_lv.getVisibility()==View.VISIBLE){
                    panel_lv.setVisibility(View.GONE);
                }else{
                    panel_lv.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
