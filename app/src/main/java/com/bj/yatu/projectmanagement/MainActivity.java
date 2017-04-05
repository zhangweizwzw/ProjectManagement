package com.bj.yatu.projectmanagement;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.activity.BaseActivity;
import com.bj.yatu.projectmanagement.fragment.FinishFragment;
import com.bj.yatu.projectmanagement.fragment.NoFinishFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String TAG="MainActivity";
    // 定义Fragment对象
    private NoFinishFragment noFinishFragment;
    private FinishFragment finishFragment;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private RelativeLayout nofinish_layout;
    private RelativeLayout finish_layout;
    private TextView nofinish_text;
    private TextView finish_text;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        initView();
        setChioceItem(0);   // 初始化页面加载时显示第一个选项卡
    }

    /**
     * 初始化页面
     */
    private void initView() {
        // 初始化底部导航栏的控件
        nofinish_text = (TextView) findViewById(R.id.nofinish_text);
        finish_text = (TextView) findViewById(R.id.finish_text);

        nofinish_layout = (RelativeLayout) findViewById(R.id.nofinish_layout);
        finish_layout = (RelativeLayout) findViewById(R.id.finish_layout);

        nofinish_layout.setOnClickListener(this);
        finish_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nofinish_layout:
                setChioceItem(0);
                break;
            case R.id.finish_layout:
                setChioceItem(1);
                break;
            default:
                break;
        }
    }

    /**
     * 设置点击选项卡的事件处理
     * @param index 选项卡的标号：0, 1, 2
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                nofinish_layout.setBackgroundColor(getResources().getColor(R.color.table_color));
                // 如果fragment为空，则创建一个并添加到界面上
                if (noFinishFragment == null) {
                    noFinishFragment = new NoFinishFragment();
                    fragmentTransaction.add(R.id.frameLayout, noFinishFragment);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(noFinishFragment);
                }
                break;
            case 1:
                finish_layout.setBackgroundColor(getResources().getColor(R.color.table_color));
                if (finishFragment == null) {
                    finishFragment = new FinishFragment();
                    fragmentTransaction.add(R.id.frameLayout, finishFragment);
                } else {
                    fragmentTransaction.show(finishFragment);
                }
                break;
        }
        fragmentTransaction.commit();   // 提交
    }

    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        //位置
        nofinish_layout.setBackgroundColor(getResources().getColor(R.color.white));
        //消息
        finish_layout.setBackgroundColor(getResources().getColor(R.color.white));
    }

    /**
     * 隐藏Fragment
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (noFinishFragment != null) {
            fragmentTransaction.hide(noFinishFragment);
        }
        if (finishFragment != null) {
            fragmentTransaction.hide(finishFragment);
        }
    }
}
