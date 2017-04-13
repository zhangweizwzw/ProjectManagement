package com.bj.yatu.projectmanagement.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.model.ProjectsBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;

import java.sql.Date;
import java.util.List;

/**
 * Created by wxixis on 2017/4/5.
 */

public class NofinishAdapter extends BaseAdapter{
    private Context context;
    private List<ProjectsBean.ProjectBean> list;
    private boolean flag=true;
    private View mLastView;
    private int mLastPosition=-1;
    private int mLastVisibility;

    public NofinishAdapter(){}
    public NofinishAdapter(Context context, List<ProjectsBean.ProjectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_nofinish,null);
            init(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();



        setText(viewHolder,convertView,position);

        /**
         * textview的点击事件
         */
        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"more",Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 当前问题点开详情
         */

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.cp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (flag){
                    flag=false;
                    finalViewHolder.cp.setEllipsize(null);//文字展开
                    finalViewHolder.cp.setSingleLine(flag);

                }else {
                    flag=true;
                    finalViewHolder.cp.setEllipsize(TextUtils.TruncateAt.END);//收缩
                    finalViewHolder.cp.setSingleLine(flag);
                }
                Toast.makeText(context,"当前问题",Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * hint展开收缩
         */
        if(mLastPosition == position){

            viewHolder.hint.setVisibility(mLastVisibility);

        }else{

            viewHolder.hint.setVisibility(View.GONE);

        }

        return convertView;
    }

    /**
     *
     * @param viewHolder
     * @param convertView
     */
    private void setText(ViewHolder viewHolder, View convertView,int position) {

        String todayDate=Dateutil.getTodayDate();//获取当前时间
        String endData=list.get(position).getProject_end_time();
        viewHolder.name.setText("项目名称："+list.get(position).getProject_name());//项目名
        viewHolder.pb.setProgress((int) list.get(position).getTotalpercent());//百分比

        viewHolder.hint_name.setText(list.get(position).getProject_name());//项目名称
        viewHolder.fzr.setText("项目经理:"+list.get(position).getProject_fzr());//项目经理
        viewHolder.begin_time.setText("起始时间："+list.get(position).getProject_begin_time());//起始时间
        viewHolder.current_time.setText("当前时间："+ todayDate);//当前时间
        viewHolder.end_time.setText("预计完成时间:"+endData);//预计完成时间
        viewHolder.plan.setText("计划："+list.get(position).getPlans());//计划
        viewHolder.fact.setText("实际："+ list.get(position).getProject_fact());//实际
        viewHolder.personcost.setText("人工："+list.get(position).getTotalpersoncost());//人工
        viewHolder.extracost.setText("费用："+list.get(position).getTotalextracost());//费用
        viewHolder.totalcost.setText("费用小计："+list.get(position).getTotalcost());//费用小计
        //当前问题可能为空
        String quesrion=list.get(position).getQuestions();
        if (quesrion!=null){
            viewHolder.cp.setText("当前问题："+list.get(position).getQuestions());//当前问题
        }else{
            viewHolder.cp.setVisibility(View.GONE);
        }
        /**
         * 判断是否超时，更改背景颜色
         */
        if (Date.valueOf(todayDate).after(Date.valueOf(endData))){
            Log.i("==比较日期大小===","超时");
//            viewHolder.linear.setBackground(context.getResources().getColor(Color.RED));
            viewHolder.linear.setBackgroundColor(Color.RED);
        }else{
            Log.i("===比较日期大小===","没有超时");
        }

    }

    /**
     *
     * @param viewHolder
     * @param convertView
     */
    private void init(ViewHolder viewHolder, View convertView) {
        viewHolder.name= (TextView) convertView.findViewById(R.id.nofinishadapter_tv);
        viewHolder.pb= (ProgressBar) convertView.findViewById(R.id.nofinishadapter_pb);
        viewHolder.hint=convertView.findViewById(R.id.include);
        viewHolder.more= (TextView) convertView.findViewById(R.id.hint_more);
        viewHolder.cp= (TextView) convertView.findViewById(R.id.hint_curentproblem);//百分比
        viewHolder.linear= (LinearLayout) convertView.findViewById(R.id.linaear);//是否超时的背景
        viewHolder.hint_name= (TextView) convertView.findViewById(R.id.hint_name);//项目名称
        viewHolder.fzr= (TextView) convertView.findViewById(R.id.hint_pm);//项目经理
        viewHolder.begin_time= (TextView) convertView.findViewById(R.id.hinttime);//起始时间
        viewHolder.current_time= (TextView) convertView.findViewById(R.id.hint_currenttime);//当前时间
        viewHolder.end_time= (TextView) convertView.findViewById(R.id.predicttime);//预计完成时间
        viewHolder.plan= (TextView) convertView.findViewById(R.id.hint_plan);//计划
        viewHolder.fact= (TextView) convertView.findViewById(R.id.hint_actual);//实际
        viewHolder.personcost= (TextView) convertView.findViewById(R.id.hint_manual);//人工
        viewHolder.extracost= (TextView) convertView.findViewById(R.id.hint_coast);//费用
        viewHolder.totalcost= (TextView) convertView.findViewById(R.id.coastsubtotal);//费用小计
    }


    class ViewHolder{
        TextView name,hint_name,//项目名称
                fzr,//项目经理
                begin_time,//起始时间
                end_time,//结束时间
                current_time,//当前时间
                plan,//计划
                fact,//实际
                personcost,//人工
                extracost,//费用
                totalcost,//小计
                more,
                cp;//当前问题
        ProgressBar pb;//百分比
        View hint;
        LinearLayout linear;
    }

    public void changeImageVisable(View view,int position) {
        if(mLastView != null && mLastPosition != position ) {
            ViewHolder holder = (ViewHolder) mLastView.getTag();
            switch(holder.hint.getVisibility()) {
                case View.VISIBLE:
                    holder.hint.setVisibility(View.GONE);
                    mLastVisibility = View.GONE;
                    break;
                default :
                    break;

            }

        }

        mLastPosition = position;
        mLastView = view;
        ViewHolder holder = (ViewHolder) view.getTag();
        switch(holder.hint.getVisibility()) {
            case View.GONE:
                holder.hint.setVisibility(View.VISIBLE);
                mLastVisibility = View.VISIBLE;
                break;
            case View.VISIBLE:
                holder.hint.setVisibility(View.GONE);
                mLastVisibility = View.GONE;
                break;
        }

    }

}
