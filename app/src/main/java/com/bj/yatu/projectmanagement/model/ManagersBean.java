package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public class ManagersBean {

    /**
     * manager : [{"id":1,"account":"admin","password":"111111","username":"王世永","remark":2},{"id":2,"account":"administrator","password":"000000","username":"管理员","remark":2}]
     * status : true
     */

    private boolean status;
    private List<ManagerBean> manager;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ManagerBean> getManager() {
        return manager;
    }

    public void setManager(List<ManagerBean> manager) {
        this.manager = manager;
    }

    public static class ManagerBean {
        /**
         * id : 1
         * account : admin
         * password : 111111
         * username : 王世永
         * remark : 2
         */

        private int id;
        private String account;
        private String password;
        private String username;
        private int remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getRemark() {
            return remark;
        }

        public void setRemark(int remark) {
            this.remark = remark;
        }
    }
}
