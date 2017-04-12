package com.bj.yatu.projectmanagement.common;

import android.app.Application;

/**
 * Created by admin on 2017/4/5.
 */

public class MyApplication extends Application{
    public static String account="";//用户账号

    public static String username="";//用户姓名

    public static int identity=0; //用户身份   0/1/2   领导/生产主管/项目经理


    @Override
    public void onCreate() {
        super.onCreate();


    }
}
