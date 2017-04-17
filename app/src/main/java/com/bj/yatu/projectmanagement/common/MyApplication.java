package com.bj.yatu.projectmanagement.common;

import android.app.Application;

/**
 * Created by admin on 2017/4/5.
 */

public class MyApplication extends Application{
    public static String account="1";//用户账号

    public static String username="";//用户姓名

    public static int identity=1; //用户身份   0/1/2   领导/生产主管/项目经理

    public static boolean isFirstMain=true; //是否第一次进入项目




    @Override
    public void onCreate() {
        super.onCreate();


    }
}
