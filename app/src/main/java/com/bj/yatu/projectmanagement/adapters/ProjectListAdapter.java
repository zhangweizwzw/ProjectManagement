package com.bj.yatu.projectmanagement.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.activity.ProjectDetailActivity;
import com.bj.yatu.projectmanagement.common.MyProgress;
import com.bj.yatu.projectmanagement.model.ProjectsBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;

/***
 * @author Administrator
 *
 */
public class ProjectListAdapter extends BaseAdapter {
	private final String TAG="ProjectListAdapter";
	private LayoutInflater mInflater;
	private OrderViewHolder holder;
	private Activity mcontext;
	private List<ProjectsBean.ProjectBean> mData;
	private boolean flag=true;
	private boolean isfinish=true;

	public ProjectListAdapter(boolean isfinish,Activity context, List<ProjectsBean.ProjectBean> list) {
		mcontext=context;
		mInflater = LayoutInflater.from(context);
		this.mData=list;

		this.isfinish=isfinish;
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
		holder.question_tv = (TextView) convertView.findViewById(R.id.question_tv);
		holder.projectrate_pro= (MyProgress) convertView.findViewById(R.id.projectrate_pro);
		holder.largerela= (RelativeLayout) convertView.findViewById(R.id.largerela);

		convertView.setTag(holder);
//		} else {
		holder = (OrderViewHolder) convertView.getTag();
//		}

		final String projectName=projectBean.getProject_name();
		final String projectManager=projectBean.getProject_fzr();
		final String projectStarttime=projectBean.getProject_begin_time();
		final String projectNowtime= Dateutil.getTodayDate();
		final String projectFinishtime=projectBean.getProject_end_time();
		final String projectPlan=projectBean.getProject_plan();
		final String projectFact=projectBean.getProject_fact();
		final String projectPeople=projectBean.getTotalpersoncost()+"";
		final String projectmoney=projectBean.getTotalextracost()+"";
		final String projectMinmoney=projectBean.getTotalcost()+"";
		final String projectproblem=projectBean.getQuestions();
		double projectPercent=projectBean.getTotalpercent();

		holder.projectrate_pro.setProgress(projectPercent);
		holder.projectname_tv.setText(projectName);
		holder.projectManager_tv.setText(projectManager);
		holder.starttitme_tv.setText(projectStarttime);
		holder.nowtime_tv.setText(projectNowtime);
		holder.expectfinishtime_tv.setText(projectFinishtime);
		holder.expect_tv.setText(projectPlan);
		holder.fact_tv.setText(projectFact);
		holder.people_tv.setText(projectPeople);
		holder.cost_tv.setText(projectmoney);
		holder.costmin_tv.setText(projectMinmoney);
		holder.question_tv.setText(projectproblem);

		holder.question_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (flag){
					flag=false;
					holder.question_tv.setEllipsize(null);//文字展开
					holder.question_tv.setSingleLine(flag);

				}else {
					flag=true;
					holder.question_tv.setEllipsize(TextUtils.TruncateAt.END);//收缩
					holder.question_tv.setSingleLine(flag);
				}
			}
		});

		holder.more_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.i("aaaaaaaaa",projectBean.getId()+"");
				Intent intent=new Intent();
				intent.setClass(mcontext, ProjectDetailActivity.class);
				intent.putExtra("projectid",projectBean.getId()+"");
				mcontext.startActivity(intent);
			}
		});

		return convertView;

	}

	public final class OrderViewHolder {
		private TextView projectname_tv,projectManager_tv,starttitme_tv,nowtime_tv,expectfinishtime_tv,expect_tv;
		private TextView fact_tv,people_tv,cost_tv,costmin_tv,more_tv,question_tv;
		private MyProgress projectrate_pro;
		private RelativeLayout largerela;
	}

}