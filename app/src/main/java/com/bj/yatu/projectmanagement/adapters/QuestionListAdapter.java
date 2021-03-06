package com.bj.yatu.projectmanagement.adapters;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bj.yatu.projectmanagement.R;
import com.bj.yatu.projectmanagement.activity.ProjectDetailActivity;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.common.MyProgress;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.model.ProjectsBean;
import com.bj.yatu.projectmanagement.utils.Dateutil;
import com.bj.yatu.projectmanagement.utils.StringUtil;

import java.util.List;

/***
 * @author Administrator
 *
 */
public class QuestionListAdapter extends BaseAdapter {
	private final String TAG="ProjectListAdapter";
	private LayoutInflater mInflater;
	private OrderViewHolder holder;
	private Activity mcontext;
	private List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> mData;
	private boolean flag=true;

	public QuestionListAdapter(Activity context, List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> list) {
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
		final ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean qBean= mData.get(position);
		holder = new OrderViewHolder();
		convertView = mInflater.inflate(R.layout.questionlist_layout, null);
		holder.question_date = (TextView) convertView.findViewById(R.id.question_date);
		holder.question_tv = (TextView) convertView.findViewById(R.id.question_tv);
		holder.answerquestion_tv = (TextView) convertView.findViewById(R.id.answerquestion_tv);
		holder.rela1= (RelativeLayout) convertView.findViewById(R.id.rela1);
		holder.rela2= (RelativeLayout) convertView.findViewById(R.id.rela2);

		convertView.setTag(holder);
//		} else {
		holder = (OrderViewHolder) convertView.getTag();
//		}

		final String questiondate=qBean.getQuestiondate();
		final String question_answer=qBean.getNode_question_answer();
		final String question=qBean.getNode_question();

		holder.question_date.setText(questiondate);
		holder.question_tv.setText(question);
		holder.answerquestion_tv.setText(question_answer);

		final OrderViewHolder tempHolder = holder;

		tempHolder.rela1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (flag){
					flag=false;
					tempHolder.question_tv.setEllipsize(null);//文字展开
					tempHolder.question_tv.setSingleLine(flag);

				}else {
					flag=true;
					tempHolder.question_tv.setEllipsize(TextUtils.TruncateAt.END);//收缩
					tempHolder.question_tv.setSingleLine(flag);
				}

				if(tempHolder.rela2.getVisibility()==View.VISIBLE){
					tempHolder.rela2.setVisibility(View.GONE);
				}else{
					tempHolder.rela2.setVisibility(View.VISIBLE);
				}
			}
		});



		return convertView;

	}

	public final class OrderViewHolder {
		private TextView question_date,question_tv,answerquestion_tv;
		private RelativeLayout rela1,rela2;
	}

}