package com.bj.yatu.projectmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.model.AddPanelBean;
import com.bj.yatu.projectmanagement.model.MessageEvent;
import com.bj.yatu.projectmanagement.model.PanelBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by WuXiaolong on 2015/7/2.
 */
public class PanelListAdapter extends RecyclerView.Adapter<PanelListAdapter.ViewHolder> {
    private final String TAG="DiaryListAdapter";
    private Context mContext;
    private List<AddPanelBean.ProjectplansBean> dataList;

    public List<AddPanelBean.ProjectplansBean> getDataList() {
        return dataList;
    }
    public void removeAllDataList() {
        this.dataList.removeAll(dataList);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PanelListAdapter(Context context, List<AddPanelBean.ProjectplansBean> dataList) {
        this.dataList = dataList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView panelname_et,finishsign_et,endtime_et,pancelper_et,peoplecost_et,extras_et,panelstarttime_et;
        private TextView delete_bt;

        public ViewHolder(View itemView) {
            super(itemView);
            panelname_et = (TextView) itemView.findViewById(R.id.panelname_et);
            finishsign_et = (TextView) itemView.findViewById(R.id.finishsign_et);
            endtime_et = (TextView) itemView.findViewById(R.id.endtime_et);
            pancelper_et = (TextView) itemView.findViewById(R.id.pancelper_et);
            peoplecost_et = (TextView) itemView.findViewById(R.id.peoplecost_et);
            extras_et = (TextView) itemView.findViewById(R.id.extras_et);
            panelstarttime_et = (TextView) itemView.findViewById(R.id.panelstarttime_et);

            delete_bt= (TextView) itemView.findViewById(R.id.delete_bt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.panellist_layout, parent, false);
        return new ViewHolder(v);
    }

    private String plan_name;//计划名称
    private String plan_begin_time;//计划开始时间
    private String plan_end_time;//计划结束时间
    private double plan_proportion;//计划占比
    private double plan_labor_cost;//人工成本
    private double plan_extras_cost;//杂费
    private String plan_complete_flat;//计划完成标识

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.panelname_et.setText(dataList.get(position).getPlan_name());
        holder.finishsign_et.setText(dataList.get(position).getPlan_complete_flat());
        holder.endtime_et.setText(dataList.get(position).getPlan_end_time());
        holder.pancelper_et.setText(dataList.get(position).getPlan_proportion()+"");
        holder.peoplecost_et.setText(dataList.get(position).getPlan_labor_cost()+"");
        holder.extras_et.setText(dataList.get(position).getPlan_extras_cost()+"");
        holder.panelstarttime_et.setText(dataList.get(position).getPlan_begin_time());

        holder.delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent(String.valueOf(position)));
            }
        });

        if (onItemClickListener != null) {
            holder.panelname_et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.panelname_et.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}