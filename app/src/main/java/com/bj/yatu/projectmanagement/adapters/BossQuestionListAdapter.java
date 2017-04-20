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
import com.bj.yatu.projectmanagement.activity.AddQuestionandAnswerActivity;
import com.bj.yatu.projectmanagement.common.MyApplication;
import com.bj.yatu.projectmanagement.model.ProjectDetailBean;
import com.bj.yatu.projectmanagement.utils.StringUtil;

import java.util.List;

/***
 * @author Administrator
 *
 */
public class BossQuestionListAdapter extends BaseAdapter {
	private final String TAG="ProjectListAdapter";
	private LayoutInflater mInflater;
	private OrderViewHolder holder;
	private Activity mcontext;
	private List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> mData;
	private boolean flag=true;

	public BossQuestionListAdapter(Activity context, List<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> list) {
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
		convertView = mInflater.inflate(R.layout.bossquestionlist_layout, null);
		holder.question_date = (TextView) convertView.findViewById(R.id.question_date);
		holder.question_tv = (TextView) convertView.findViewById(R.id.question_tv);
		holder.answerquestion_tv = (TextView) convertView.findViewById(R.id.answerquestion_tv);
		holder.answer_tv = (TextView) convertView.findViewById(R.id.answer_tv);
		holder.rela1= (RelativeLayout) convertView.findViewById(R.id.rela1);
		holder.rela2= (RelativeLayout) convertView.findViewById(R.id.rela2);

		convertView.setTag(holder);
//		} else {
		holder = (OrderViewHolder) convertView.getTag();
//		}
		//当登录人是领导或者已完成 回复按钮都隐藏
		if(MyApplication.identity==0||MyApplication.isprojectfinish){
			holder.answer_tv.setVisibility(View.GONE);
		}else{
			holder.answer_tv.setVisibility(View.VISIBLE);
		}

		final String questiondate=qBean.getQuestiondate();
		final String question_answer=qBean.getNode_question_answer();
		final String question=qBean.getNode_question();

		if(!StringUtil.isEmpty(question_answer)){
			holder.answer_tv.setVisibility(View.GONE);
		}

		holder.question_date.setText(questiondate);
		holder.question_tv.setText(question);
		holder.answerquestion_tv.setText(question_answer);

		final OrderViewHolder tempHolder = holder;

		tempHolder.answer_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				MyApplication.position=position;
				MyApplication.questionid=qBean.getId();
				Intent intent=new Intent();
				intent.putExtra("ctype","答复");
				intent.setClass(mcontext,AddQuestionandAnswerActivity.class);
				mcontext.startActivityForResult(intent,3);
			}
		});

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
		private TextView question_date,question_tv,answerquestion_tv,answer_tv;
		private RelativeLayout rela1,rela2;
	}
}