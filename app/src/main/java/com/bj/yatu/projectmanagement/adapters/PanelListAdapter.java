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
    private List<PanelBean> dataList;
    public List<PanelBean> getDataList() {
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

    public PanelListAdapter(Context context, List<PanelBean> dataList) {
        this.dataList = dataList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView panelname_et,finishsign_et,endtime_et,pancelper_et,peoplecost_et,extras_et;
        private Button delete_bt;

        public ViewHolder(View itemView) {
            super(itemView);
            panelname_et = (TextView) itemView.findViewById(R.id.panelname_et);
            finishsign_et = (TextView) itemView.findViewById(R.id.finishsign_et);
            endtime_et = (TextView) itemView.findViewById(R.id.endtime_et);
            pancelper_et = (TextView) itemView.findViewById(R.id.pancelper_et);
            peoplecost_et = (TextView) itemView.findViewById(R.id.peoplecost_et);
            extras_et = (TextView) itemView.findViewById(R.id.extras_et);

            delete_bt= (Button) itemView.findViewById(R.id.delete_bt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.panellist_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.panelname_et.setText(dataList.get(position).getPanelname());
        holder.finishsign_et.setText(dataList.get(position).getFinishsign());
        holder.endtime_et.setText(dataList.get(position).getEndtime());
        holder.pancelper_et.setText(dataList.get(position).getPeoplecost());
        holder.peoplecost_et.setText(dataList.get(position).getPeoplecost());
        holder.extras_et.setText(dataList.get(position).getExtras());

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