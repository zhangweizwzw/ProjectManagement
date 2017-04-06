package com.bj.yatu.projectmanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.widget.MaterialSpinner;


public class AddProjectActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG="AddProjectActivity";
    private TextView add_panel;
    private static final String[] ANDROID_VERSIONS = {"王世勇", "哈哈哈", "3", "4", "5", "6"};
    private MaterialSpinner manager;
    private TextView text_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        initView();
    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("添加项目");
        add_panel= (TextView) findViewById(R.id.add_panel);
        add_panel.setOnClickListener(this);
        manager= (MaterialSpinner) findViewById(R.id.manager);
        manager.setItems(ANDROID_VERSIONS);
        manager.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Log.i(TAG,"AAA"+item);
            }
        });
        manager.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Log.i(TAG,"没有选中什么");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_panel:
                startActivity(new Intent(this,AddPanelActivity.class));
                break;
        }
    }
}
