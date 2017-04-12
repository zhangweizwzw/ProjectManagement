package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/6.
 */

public class ProjectsBean {

    /**
     * project : [{"id":2,"project_name":"项目2","project_fzrid":2,"project_sqrid":1,"project_fzr":"管理员","project_begin_time":"2017-04-12","project_end_time":"2017-04-14","project_plan":"","project_fact":"","project_isfinish":false,"totalpersoncost":0,"totalextracost":0,"totalcost":0,"totalpercent":0,"questions":"","plans":null},{"id":1,"project_name":"项目1","project_fzrid":1,"project_sqrid":1,"project_fzr":"王世永","project_begin_time":"2017-04-12","project_end_time":"2017-04-30","project_plan":"计划1","project_fact":"计划2","project_isfinish":false,"totalpersoncost":200,"totalextracost":200,"totalcost":400,"totalpercent":20,"questions":"问题222222222222222222222222222222222222222222222222","plans":null}]
     * status : true
     */

    private boolean status;
    private List<ProjectBean> project;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ProjectBean> getProject() {
        return project;
    }

    public void setProject(List<ProjectBean> project) {
        this.project = project;
    }

    public static class ProjectBean {
        /**
         * id : 2
         * project_name : 项目2
         * project_fzrid : 2
         * project_sqrid : 1
         * project_fzr : 管理员
         * project_begin_time : 2017-04-12
         * project_end_time : 2017-04-14
         * project_plan :
         * project_fact :
         * project_isfinish : false
         * totalpersoncost : 0.0
         * totalextracost : 0.0
         * totalcost : 0.0
         * totalpercent : 0.0
         * questions :
         * plans : null
         */

        private int id;
        private String project_name;
        private int project_fzrid;
        private int project_sqrid;
        private String project_fzr;
        private String project_begin_time;
        private String project_end_time;
        private String project_plan;
        private String project_fact;
        private boolean project_isfinish;
        private double totalpersoncost;
        private double totalextracost;
        private double totalcost;
        private double totalpercent;
        private String questions;
        private Object plans;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public int getProject_fzrid() {
            return project_fzrid;
        }

        public void setProject_fzrid(int project_fzrid) {
            this.project_fzrid = project_fzrid;
        }

        public int getProject_sqrid() {
            return project_sqrid;
        }

        public void setProject_sqrid(int project_sqrid) {
            this.project_sqrid = project_sqrid;
        }

        public String getProject_fzr() {
            return project_fzr;
        }

        public void setProject_fzr(String project_fzr) {
            this.project_fzr = project_fzr;
        }

        public String getProject_begin_time() {
            return project_begin_time;
        }

        public void setProject_begin_time(String project_begin_time) {
            this.project_begin_time = project_begin_time;
        }

        public String getProject_end_time() {
            return project_end_time;
        }

        public void setProject_end_time(String project_end_time) {
            this.project_end_time = project_end_time;
        }

        public String getProject_plan() {
            return project_plan;
        }

        public void setProject_plan(String project_plan) {
            this.project_plan = project_plan;
        }

        public String getProject_fact() {
            return project_fact;
        }

        public void setProject_fact(String project_fact) {
            this.project_fact = project_fact;
        }

        public boolean isProject_isfinish() {
            return project_isfinish;
        }

        public void setProject_isfinish(boolean project_isfinish) {
            this.project_isfinish = project_isfinish;
        }

        public double getTotalpersoncost() {
            return totalpersoncost;
        }

        public void setTotalpersoncost(double totalpersoncost) {
            this.totalpersoncost = totalpersoncost;
        }

        public double getTotalextracost() {
            return totalextracost;
        }

        public void setTotalextracost(double totalextracost) {
            this.totalextracost = totalextracost;
        }

        public double getTotalcost() {
            return totalcost;
        }

        public void setTotalcost(double totalcost) {
            this.totalcost = totalcost;
        }

        public double getTotalpercent() {
            return totalpercent;
        }

        public void setTotalpercent(double totalpercent) {
            this.totalpercent = totalpercent;
        }

        public String getQuestions() {
            return questions;
        }

        public void setQuestions(String questions) {
            this.questions = questions;
        }

        public Object getPlans() {
            return plans;
        }

        public void setPlans(Object plans) {
            this.plans = plans;
        }
    }
}
