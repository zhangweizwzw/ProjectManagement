package com.bj.yatu.projectmanagement.common;

import android.app.Application;

import com.bj.yatu.projectmanagement.model.ProjectDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/5.
 */

public class MyApplication extends Application{
    public static String account="1";//用户账号

    public static int position=0;//回复position
    public static int questionid=0;//回复问题id
    public static String questioncontent="";//回复问题内容

    public static int identity=1; //用户身份   0/1/2   领导/生产主管/项目经理

    public static boolean isFirstMain=true; //是否第一次进入项目

    public static boolean isprojectfinish=false; //项目是否完成

    public static ArrayList<ProjectDetailBean.ProjectBean.ProjectplansBean.NodesBean.QuestionsBean> qestionlist; //问题集合







    @Override
    public void onCreate() {
        super.onCreate();


    }
}
