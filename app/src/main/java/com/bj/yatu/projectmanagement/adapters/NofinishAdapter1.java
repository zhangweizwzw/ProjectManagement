package com.bj.yatu.projectmanagement.adapters;

import java.util.List;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.Info;

/***
 * 带复选框的listView的适配器
 * @author Administrator
 *
 */
public class NofinishAdapter1 extends BaseAdapter {

    private LayoutInflater mInflater;
    private OrderViewHolder holder;
    private Activity mcontext;
    private List<Info> mData;
    boolean flag=true;

    public NofinishAdapter1(Activity context,List<Info> list) {
        mcontext=context;
        mInflater = LayoutInflater.from(context);
        this.mData=list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = null;
        //convertView为null的时候初始化convertView。
        final Info info= mData.get(position);
        holder = new OrderViewHolder();
        convertView = mInflater.inflate(R.layout.adapter_nofinish, null);
        holder.tv= (TextView) convertView.findViewById(R.id.nofinishadapter_tv);
        holder.hint_curentproblem= (TextView) convertView.findViewById(R.id.hint_curentproblem);
        holder.pb= (ProgressBar) convertView.findViewById(R.id.nofinishadapter_pb);
        holder.linaear= (LinearLayout) convertView.findViewById(R.id.linaear);
        holder.hint=convertView.findViewById(R.id.include);

        convertView.setTag(holder);
//		} else {
        holder = (OrderViewHolder) convertView.getTag();
//		}
        holder.tv.setText(info.getName());
        holder.pb.setProgress(info.getProgress());


        final OrderViewHolder tempHolder = holder;

        tempHolder.hint.setVisibility(View.GONE);
        tempHolder.linaear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempHolder.hint.getVisibility() ==View.VISIBLE){
                    tempHolder.hint.setVisibility(View.GONE);
                }else{
                    tempHolder.hint.setVisibility(View.VISIBLE);
                }
            }
        });

        tempHolder.hint_curentproblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag){
                    flag=false;
                    tempHolder.hint_curentproblem.setEllipsize(null);//文字展开
                    tempHolder.hint_curentproblem.setSingleLine(flag);

                }else {
                    flag=true;
                    tempHolder.hint_curentproblem.setEllipsize(TextUtils.TruncateAt.END);//收缩
                    tempHolder.hint_curentproblem.setSingleLine(flag);
                }
            }
        });




        return convertView;
    }

    public final class OrderViewHolder {
        TextView tv,hint_curentproblem;
        ProgressBar pb;
        View hint;
        TextView more,cp;
        LinearLayout linaear;


    }

}