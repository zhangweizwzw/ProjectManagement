package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */

public class AddPanelBean {

    /**
     * id :
     * project_name : 项目1
     * project_sqrid : 1
     * project_fzr : 王世永
     * project_fzrid : 1
     * project_begin_time : 2017-04-12 14:46:33
     * project_end_time : 2017-04-30 14:46:42
     * projectplans : [{"plan_name":"计划1","plan_begin_time":"2017-04-10 14:47:37","plan_end_time":"2017-04-14 14:47:44","plan_proportion":20,"plan_labor_cost":100,"plan_extras_cost":100,"plan_complete_flat":"计划1完成标识"}]
     */

    private String id;//项目id
    private String project_name;//项目名称
    private String project_sqrid;//申请人
    private String project_fzr;//项目经理
    private String project_fzrid;//项目经理id
    private String project_begin_time;//开始时间
    private String project_end_time;//结束时间
    private List<ProjectplansBean> projectplans;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_sqrid() {
        return project_sqrid;
    }

    public void setProject_sqrid(String project_sqrid) {
        this.project_sqrid = project_sqrid;
    }

    public String getProject_fzrid() {
        return project_fzrid;
    }

    public void setProject_fzrid(String project_fzrid) {
        this.project_fzrid = project_fzrid;
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

    public List<ProjectplansBean> getProjectplans() {
        return projectplans;
    }

    public void setProjectplans(List<ProjectplansBean> projectplans) {
        this.projectplans = projectplans;
    }

    public static class ProjectplansBean {
        /**
         * plan_name : 计划1
         * plan_begin_time : 2017-04-10 14:47:37
         * plan_end_time : 2017-04-14 14:47:44
         * plan_proportion : 20.0
         * plan_labor_cost : 100.0
         * plan_extras_cost : 100.0
         * plan_complete_flat : 计划1完成标识
         */

        private String plan_name;//计划名称
        private String plan_begin_time;//计划开始时间
        private String plan_end_time;//计划结束时间
        private double plan_proportion;//计划占比
        private double plan_labor_cost;//人工成本
        private double plan_extras_cost;//杂费
        private String plan_complete_flat;//计划完成标识

        public String getPlan_name() {
            return plan_name;
        }

        public void setPlan_name(String plan_name) {
            this.plan_name = plan_name;
        }

        public String getPlan_begin_time() {
            return plan_begin_time;
        }

        public void setPlan_begin_time(String plan_begin_time) {
            this.plan_begin_time = plan_begin_time;
        }

        public String getPlan_end_time() {
            return plan_end_time;
        }

        public void setPlan_end_time(String plan_end_time) {
            this.plan_end_time = plan_end_time;
        }

        public double getPlan_proportion() {
            return plan_proportion;
        }

        public void setPlan_proportion(double plan_proportion) {
            this.plan_proportion = plan_proportion;
        }

        public double getPlan_labor_cost() {
            return plan_labor_cost;
        }

        public void setPlan_labor_cost(double plan_labor_cost) {
            this.plan_labor_cost = plan_labor_cost;
        }

        public double getPlan_extras_cost() {
            return plan_extras_cost;
        }

        public void setPlan_extras_cost(double plan_extras_cost) {
            this.plan_extras_cost = plan_extras_cost;
        }

        public String getPlan_complete_flat() {
            return plan_complete_flat;
        }

        public void setPlan_complete_flat(String plan_complete_flat) {
            this.plan_complete_flat = plan_complete_flat;
        }
    }
}
