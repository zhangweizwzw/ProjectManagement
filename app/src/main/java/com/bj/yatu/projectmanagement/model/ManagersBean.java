package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public class ManagersBean {


    /**
     * manager : [{"id":5,"account":"manager","password":"670b14728ad9902aecba32e22fa4f6bd","userName":"manager1","sex":"M","email":"","addr":"","fixedTelephone":"","phone":"","idNumber":"00001","code":"001","remark":"","avatarImage":"","status":true,"createDate":1492654992826,"createOpName":"管理员","source":0,"org":"","roles":""}]
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
         * id : 5
         * account : manager
         * password : 670b14728ad9902aecba32e22fa4f6bd
         * userName : manager1
         * sex : M
         * email :
         * addr :
         * fixedTelephone :
         * phone :
         * idNumber : 00001
         * code : 001
         * remark :
         * avatarImage :
         * status : true
         * createDate : 1492654992826
         * createOpName : 管理员
         * source : 0
         * org :
         * roles :
         */

        private int id;
        private String account;
        private String password;
        private String userName;
        private String sex;
        private String email;
        private String addr;
        private String fixedTelephone;
        private String phone;
        private String idNumber;
        private String code;
        private String remark;
        private String avatarImage;
        private boolean status;
        private long createDate;
        private String createOpName;
        private int source;
        private String org;
        private String roles;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getFixedTelephone() {
            return fixedTelephone;
        }

        public void setFixedTelephone(String fixedTelephone) {
            this.fixedTelephone = fixedTelephone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAvatarImage() {
            return avatarImage;
        }

        public void setAvatarImage(String avatarImage) {
            this.avatarImage = avatarImage;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCreateOpName() {
            return createOpName;
        }

        public void setCreateOpName(String createOpName) {
            this.createOpName = createOpName;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }
    }
}
