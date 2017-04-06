package com.bj.yatu.projectmanagement.common;

/**
 * Created by wxixis on 2017/4/5.
 */

public class Info {
    //项目名称
    private String name;
    //项目进度
    private int progress;
    //项目经理
    private String pm;
    //起始时间
    private String startTime;
    //当前时间
    private String currentTime;
    //预计完成时间
    private String predictTime;
    //计划
    private String plan;
    //实际
    private String actual;
    //人工
    private String manual;
    //费用
    private String coast;
    //费用小计
    private String coastSubtotal;
    public Info(){}
    public Info(String name, int progress) {
        this.name = name;
        this.progress = progress;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public String getCoastSubtotal() {
        return coastSubtotal;
    }

    public void setCoastSubtotal(String coastSubtotal) {
        this.coastSubtotal = coastSubtotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(String predictTime) {
        this.predictTime = predictTime;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
