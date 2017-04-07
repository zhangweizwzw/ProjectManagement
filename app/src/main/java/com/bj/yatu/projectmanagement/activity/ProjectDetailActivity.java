package com.bj.yatu.projectmanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.model.One;
import com.bj.yatu.projectmanagement.model.PanelBean;
import com.bj.yatu.projectmanagement.model.Three;
import com.bj.yatu.projectmanagement.model.Two;
import com.bj.yatu.projectmanagement.widget.NestFullListView;
import com.bj.yatu.projectmanagement.widget.NestFullListViewAdapter;
import com.bj.yatu.projectmanagement.widget.NestFullViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_center;
    private ImageView image_left;
    private RelativeLayout rela2;
    private ListView panel_lv;
    private List<PanelBean> panelList=new ArrayList<PanelBean>();
    //
    private List<One> ailist=new ArrayList<One>();
    private  NestFullListView cstFullShowListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);


        initData();
        initView();
    }

    private void initData() {
        List<Three> tlist=new ArrayList<Three>();
        Three three1=new Three();
        three1.setId("1");
        three1.setTime("时间");
        three1.setQuestion("问题描述");
        three1.setDepict("领导回复");

        Three three2=new Three();
        three2.setId("2");
        three2.setTime("时间");
        three2.setQuestion("问题描述");
        three2.setDepict("领导回复");

        tlist.add(three1);
        tlist.add(three2);


        List<Two> llist=new ArrayList<Two>();
        Two two1=new Two();
        two1.setAge("12");
        two1.setName("节点明细内容");
        two1.setTlist(tlist);

        Two two2=new Two();
        two2.setAge("12");
        two2.setName("节点明细内容");
        two2.setTlist(tlist);

        llist.add(two1);
        llist.add(two2);


        One one1=new One();
        one1.setId("一");
        one1.setName("项目节点中的内容");
        one1.setLlist(llist);

        One one2=new One();
        one2.setId("二");
        one2.setName("项目节点中的内容");
        one2.setLlist(llist);

        One one3=new One();
        one3.setId("三");
        one3.setName("项目节点中的内容");
        one3.setLlist(llist);

        ailist.add(one1);
        ailist.add(one2);
        ailist.add(one3);
    }

    private void initView() {
        text_center= (TextView) findViewById(R.id.text_center);
        text_center.setText("项目详情");
        image_left= (ImageView) findViewById(R.id.image_left);
        image_left.setImageResource(R.mipmap.title_back);
        image_left.setOnClickListener(this);
        rela2= (RelativeLayout) findViewById(R.id.rela2);
        rela2.setOnClickListener(this);

        cstFullShowListView= (NestFullListView) findViewById(R.id.panel_lv);

        cstFullShowListView.setAdapter(new NestFullListViewAdapter<One>(R.layout.item_lv, ailist) {
            @Override
            public void onBind(int pos, One one, final NestFullViewHolder holder) {
                holder.setText(R.id.tv, one.getName());

                final NestFullListView nestFullListView=(NestFullListView)holder.getView(R.id.cstFullShowListView2);

                ((LinearLayout)holder.getView(R.id.lin1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(((NestFullListView)holder.getView(R.id.cstFullShowListView2)).getVisibility()==View.VISIBLE){
                            ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setVisibility(View.GONE);
                        }else{
                            ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setVisibility(View.VISIBLE);
                        }
                    }
                });

                ((NestFullListView)holder.getView(R.id.cstFullShowListView2)).setAdapter(new NestFullListViewAdapter<Two>(R.layout.item_lv2, one.getLlist()) {
                    @Override
                    public void onBind(int pos,Two two,final NestFullViewHolder holder) {
                        holder.setText(R.id.tv, two.getName());

                        final NestFullListView cstFullShowListView3=(NestFullListView)holder.getView(R.id.cstFullShowListView3);

                        ((LinearLayout)holder.getView(R.id.lin2)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(((NestFullListView)holder.getView(R.id.cstFullShowListView3)).getVisibility()==View.VISIBLE){
                                    ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setVisibility(View.GONE);
                                }else{
                                    ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setVisibility(View.VISIBLE);
                                }
                            }
                        });

                        ((NestFullListView)holder.getView(R.id.cstFullShowListView3)).setAdapter(new NestFullListViewAdapter<Three>(R.layout.item_lv3, two.getTlist()) {
                            @Override
                            public void onBind(int pos, Three three,final NestFullViewHolder holder) {
                                holder.setText(R.id.tv, three.getTime());
                                holder.setText(R.id.question_tv, three.getQuestion());
                                holder.setText(R.id.depict_tv, three.getDepict());

                                ((LinearLayout)holder.getView(R.id.lin3)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if( ((RelativeLayout)holder.getView(R.id.question_rela)).getVisibility()==View.VISIBLE){
                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.GONE);
                                        }else{
                                            ((RelativeLayout)holder.getView(R.id.question_rela)).setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                        });


                    }
                });

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_left:
                finish();
                break;
            case R.id.rela2:
                if(cstFullShowListView.getVisibility()==View.VISIBLE){
                    cstFullShowListView.setVisibility(View.GONE);
                }else{
                    cstFullShowListView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
