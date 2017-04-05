package com.bj.yatu.projectmanagement.common;

/**
 * Created by wxixis on 2017/4/5.
 */

public class Info {
    //项目名称
    private String name;
    //项目进度
    private String progress;
    //起始时间
    private String startTime;
    //当前时间
    private String currentTime;
    //预计完成时间
    private String predictTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
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
}
