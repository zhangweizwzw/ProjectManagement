package com.bj.yatu.projectmanagement.model;

/**
 * Created by admin on 2017/4/12.
 */

public class UserLoginBean {

    /**
     * user : {"id":1,"account":"admin","password":"111111","username":"王世永","remark":2}
     * status : true
     */

    private UserBean user;
    private boolean status;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class UserBean {
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
