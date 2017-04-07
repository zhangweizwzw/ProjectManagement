package com.bj.yatu.projectmanagement.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.common.Info;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by wxixis on 2017/4/5.
 */

public class NofinishAdapter extends BaseAdapter{
    private Context context;
    private List<Info> list;
    private boolean flag=true;
    private View mLastView;
    private int mLastPosition=-1;
    private int mLastVisibility;

    public NofinishAdapter(Context context, List<Info> list) {
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
            viewHolder.tv= (TextView) convertView.findViewById(R.id.nofinishadapter_tv);
            viewHolder.pb= (ProgressBar) convertView.findViewById(R.id.nofinishadapter_pb);
            viewHolder.hint=convertView.findViewById(R.id.include);
            viewHolder.more= (TextView) convertView.findViewById(R.id.hint_more);
            viewHolder.cp= (TextView) convertView.findViewById(R.id.hint_curentproblem);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.tv.setText(list.get(position).getName());
        viewHolder.pb.setProgress(list.get(position).getProgress());

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


    class ViewHolder{
        TextView tv;
        ProgressBar pb;
        View hint;
        TextView more,cp;
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
