package com.bj.yatu.projectmanagement.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bj.yatu.projectmanagement.common.Info;

import java.util.List;

/**
 * Created by wxixis on 2017/4/5.
 */

public class NofinishAdapter extends BaseAdapter{
    private Context context;
    private List<Info> list;

    public NofinishAdapter(Context context, List<Info> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        return null;
    }
}
