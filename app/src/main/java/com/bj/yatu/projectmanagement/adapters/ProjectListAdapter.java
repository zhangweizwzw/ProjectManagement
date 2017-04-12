package com.bj.yatu.projectmanagement.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.activity.ProjectDetailActivity;
import com.bj.yatu.projectmanagement.model.ProjectBean;
import com.bj.yatu.projectmanagement.common.MyProgress;
import com.bj.yatu.projectmanagement.model.ProjectsBean;

/***
 * 带复选框的listView的适配器
 * @author Administrator
 *
 */
public class ProjectListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private OrderViewHolder holder;
	private Activity mcontext;
	private List<ProjectsBean.ProjectBean> mData;

	public ProjectListAdapter(Activity context, List<ProjectsBean.ProjectBean> list) {
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
		final ProjectsBean.ProjectBean projectBean= mData.get(position);
		holder = new OrderViewHolder();
		convertView = mInflater.inflate(R.layout.projectlist_item, null);
		holder.projectname_tv = (TextView) convertView.findViewById(R.id.projectname_tv);
		holder.projectManager_tv = (TextView) convertView.findViewById(R.id.projectManager_tv);
		holder.starttitme_tv = (TextView) convertView.findViewById(R.id.starttitme_tv);
		holder.nowtime_tv = (TextView) convertView.findViewById(R.id.nowtime_tv);
		holder.expectfinishtime_tv = (TextView) convertView.findViewById(R.id.expectfinishtime_tv);
		holder.expect_tv = (TextView) convertView.findViewById(R.id.expect_tv);
		holder.fact_tv = (TextView) convertView.findViewById(R.id.fact_tv);
		holder.people_tv = (TextView) convertView.findViewById(R.id.people_tv);
		holder.cost_tv = (TextView) convertView.findViewById(R.id.cost_tv);
		holder.costmin_tv = (TextView) convertView.findViewById(R.id.costmin_tv);
		holder.more_tv = (TextView) convertView.findViewById(R.id.more_tv);
		holder.projectrate_pro= (MyProgress) convertView.findViewById(R.id.projectrate_pro);

		convertView.setTag(holder);
//		} else {
		holder = (OrderViewHolder) convertView.getTag();
//		}

//		final String projectName=projectBean.getProject_name();
//		final String projectRate=projectBean.getProjectRate();
//		final String projectManager=projectBean.getProjectManager();
//		final String projectStarttime=projectBean.getProject_begin_time();
//		final String projectNowtime=projectBean.getProjectNowtime();
//		final String projectFinishtime=projectBean.getProject_end_time();
//		final String projectPlan=projectBean.getProjectPlan();
//		final String projectFact=projectBean.getProjectFact();
//		final String projectPeople=projectBean.getProjectPeople();
//		final String projectmoney=projectBean.getProjectmoney();
//		final String projectMinmoney=projectBean.getProjectMinmoney();
//		final String projectproblem=projectBean.getProjectproblem();
//
//		holder.projectrate_pro.setProgress(Integer.parseInt(projectRate));
//		holder.projectname_tv.setText(projectName);
//		holder.projectManager_tv.setText(projectManager);
//		holder.starttitme_tv.setText(projectStarttime);
//		holder.nowtime_tv.setText(projectNowtime);
//		holder.expectfinishtime_tv.setText(projectFinishtime);
//		holder.expect_tv.setText(projectPlan);
//		holder.fact_tv.setText(projectFact);
//		holder.people_tv.setText(projectPeople);
//		holder.cost_tv.setText(projectmoney);
//		holder.costmin_tv.setText(projectMinmoney);

		holder.more_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mcontext.startActivity(new Intent(mcontext, ProjectDetailActivity.class));
			}
		});

		return convertView;

	}

	public final class OrderViewHolder {
		private TextView projectname_tv,projectManager_tv,starttitme_tv,nowtime_tv,expectfinishtime_tv,expect_tv;
		private TextView fact_tv,people_tv,cost_tv,costmin_tv,more_tv;
		private MyProgress projectrate_pro;
	}

}