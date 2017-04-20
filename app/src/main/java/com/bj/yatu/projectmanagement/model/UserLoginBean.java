package com.bj.yatu.projectmanagement.model;

/**
 * Created by admin on 2017/4/12.
 */

public class UserLoginBean {


    /**
     * remark : 领导
     * id : 7
     * username : 领导
     * status : true
     */

    private String remark;
    private int id;
    private String username;
    private boolean status;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
