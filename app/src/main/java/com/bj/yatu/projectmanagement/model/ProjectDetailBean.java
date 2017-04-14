package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/13.
 */

public class ProjectDetailBean {
    /**
     * project : {"id":1,"project_name":"项目1","project_sqrid":1,"project_create_time":"2017-04-12 14:46:38","project_fzrid":1,"project_begin_time":"2017-04-12 14:46:33","project_end_time":"2017-04-30 14:46:42","project_plan":null,"project_fact":null,"project_isfinish":null,"projectplans":[{"id":1,"plan_name":"计划1","plan_create_time":"2017-04-10 14:47:42","plan_begin_time":"2017-04-10 14:47:37","plan_end_time":"2017-04-14 14:47:44","plan_proportion":20,"plan_labor_cost":100,"plan_extras_cost":100,"plan_complete_flat":"计划1完成标识","nodes":[{"id":1,"node_name":"节点1","node_complete_flat":"节点1完成标识","node_create_time":"2017-04-12 14:49:40","node_begin_time":"2017-04-12 14:49:32","node_end_time":"2017-04-13 14:49:44","node_proportion":100,"node_labor_cost":50,"node_extras_cost":50,"questions":[{"id":1,"node_question":"问题111111111111111111111111111111111111111111111111","node_question_answer":null,"questiondate":null,"answerdate":null},{"id":2,"node_question":"问题222222222222222222222222222222222222222222222222","node_question_answer":null,"questiondate":null,"answerdate":null}]}]},{"id":2,"plan_name":"计划2","plan_create_time":"2017-04-12 14:49:04","plan_begin_time":"2017-04-14 14:48:45","plan_end_time":"2017-04-16 14:49:10","plan_proportion":50,"plan_labor_cost":200,"plan_extras_cost":200,"plan_complete_flat":"计划2完成标识","nodes":[]}]}
     * status : true
     */

    private ProjectBean project;
    private boolean status;

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class ProjectBean {
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

        /**
         * id : 1
         * project_name : 项目1
         * project_sqrid : 1
         * project_create_time : 2017-04-12 14:46:38
         * project_fzrid : 1
         * project_begin_time : 2017-04-12 14:46:33
         * project_end_time : 2017-04-30 14:46:42
         * project_plan : null
         * project_fact : null
         * project_isfinish : null
         * projectplans : [{"id":1,"plan_name":"计划1","plan_create_time":"2017-04-10 14:47:42","plan_begin_time":"2017-04-10 14:47:37","plan_end_time":"2017-04-14 14:47:44","plan_proportion":20,"plan_labor_cost":100,"plan_extras_cost":100,"plan_complete_flat":"计划1完成标识","nodes":[{"id":1,"node_name":"节点1","node_complete_flat":"节点1完成标识","node_create_time":"2017-04-12 14:49:40","node_begin_time":"2017-04-12 14:49:32","node_end_time":"2017-04-13 14:49:44","node_proportion":100,"node_labor_cost":50,"node_extras_cost":50,"questions":[{"id":1,"node_question":"问题111111111111111111111111111111111111111111111111","node_question_answer":null,"questiondate":null,"answerdate":null},{"id":2,"node_question":"问题222222222222222222222222222222222222222222222222","node_question_answer":null,"questiondate":null,"answerdate":null}]}]},{"id":2,"plan_name":"计划2","plan_create_time":"2017-04-12 14:49:04","plan_begin_time":"2017-04-14 14:48:45","plan_end_time":"2017-04-16 14:49:10","plan_proportion":50,"plan_labor_cost":200,"plan_extras_cost":200,"plan_complete_flat":"计划2完成标识","nodes":[]}]
         */

        private int id;
        private String project_name;
        private int project_sqrid;
        private String project_create_time;
        private int project_fzrid;
        private String project_begin_time;
        private String project_end_time;
        private String project_plan;
        private String project_fact;
        private Object project_isfinish;
        private String project_fzr;
        private List<ProjectplansBean> projectplans;


        public String getProject_fzr() {
            return project_fzr;
        }

        public void setProject_fzr(String project_fzr) {
            this.project_fzr = project_fzr;
        }

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

        public int getProject_sqrid() {
            return project_sqrid;
        }

        public void setProject_sqrid(int project_sqrid) {
            this.project_sqrid = project_sqrid;
        }

        public String getProject_create_time() {
            return project_create_time;
        }

        public void setProject_create_time(String project_create_time) {
            this.project_create_time = project_create_time;
        }

        public int getProject_fzrid() {
            return project_fzrid;
        }

        public void setProject_fzrid(int project_fzrid) {
            this.project_fzrid = project_fzrid;
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





        public Object getProject_isfinish() {
            return project_isfinish;
        }

        public void setProject_isfinish(Object project_isfinish) {
            this.project_isfinish = project_isfinish;
        }

        public List<ProjectplansBean> getProjectplans() {
            return projectplans;
        }

        public void setProjectplans(List<ProjectplansBean> projectplans) {
            this.projectplans = projectplans;
        }

        public static class ProjectplansBean {
            /**
             * id : 1
             * plan_name : 计划1
             * plan_create_time : 2017-04-10 14:47:42
             * plan_begin_time : 2017-04-10 14:47:37
             * plan_end_time : 2017-04-14 14:47:44
             * plan_proportion : 20.0
             * plan_labor_cost : 100.0
             * plan_extras_cost : 100.0
             * plan_complete_flat : 计划1完成标识
             * nodes : [{"id":1,"node_name":"节点1","node_complete_flat":"节点1完成标识","node_create_time":"2017-04-12 14:49:40","node_begin_time":"2017-04-12 14:49:32","node_end_time":"2017-04-13 14:49:44","node_proportion":100,"node_labor_cost":50,"node_extras_cost":50,"questions":[{"id":1,"node_question":"问题111111111111111111111111111111111111111111111111","node_question_answer":null,"questiondate":null,"answerdate":null},{"id":2,"node_question":"问题222222222222222222222222222222222222222222222222","node_question_answer":null,"questiondate":null,"answerdate":null}]}]
             */

            private int id;
            private String plan_name;
            private String plan_create_time;
            private String plan_begin_time;
            private String plan_end_time;
            private double plan_proportion;
            private double plan_labor_cost;
            private double plan_extras_cost;
            private String plan_complete_flat;
            private List<NodesBean> nodes;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPlan_name() {
                return plan_name;
            }

            public void setPlan_name(String plan_name) {
                this.plan_name = plan_name;
            }

            public String getPlan_create_time() {
                return plan_create_time;
            }

            public void setPlan_create_time(String plan_create_time) {
                this.plan_create_time = plan_create_time;
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

            public List<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean> nodes) {
                this.nodes = nodes;
            }

            public static class NodesBean {
                /**
                 * id : 1
                 * node_name : 节点1
                 * node_complete_flat : 节点1完成标识
                 * node_create_time : 2017-04-12 14:49:40
                 * node_begin_time : 2017-04-12 14:49:32
                 * node_end_time : 2017-04-13 14:49:44
                 * node_proportion : 100.0
                 * node_labor_cost : 50.0
                 * node_extras_cost : 50.0
                 * questions : [{"id":1,"node_question":"问题111111111111111111111111111111111111111111111111","node_question_answer":null,"questiondate":null,"answerdate":null},{"id":2,"node_question":"问题222222222222222222222222222222222222222222222222","node_question_answer":null,"questiondate":null,"answerdate":null}]
                 */

                private int id;
                private String node_name;
                private String node_complete_flat;
                private String node_create_time;
                private String node_begin_time;
                private String node_end_time;
                private double node_proportion;
                private double node_labor_cost;
                private double node_extras_cost;
                private List<QuestionsBean> questions;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNode_name() {
                    return node_name;
                }

                public void setNode_name(String node_name) {
                    this.node_name = node_name;
                }

                public String getNode_complete_flat() {
                    return node_complete_flat;
                }

                public void setNode_complete_flat(String node_complete_flat) {
                    this.node_complete_flat = node_complete_flat;
                }

                public String getNode_create_time() {
                    return node_create_time;
                }

                public void setNode_create_time(String node_create_time) {
                    this.node_create_time = node_create_time;
                }

                public String getNode_begin_time() {
                    return node_begin_time;
                }

                public void setNode_begin_time(String node_begin_time) {
                    this.node_begin_time = node_begin_time;
                }

                public String getNode_end_time() {
                    return node_end_time;
                }

                public void setNode_end_time(String node_end_time) {
                    this.node_end_time = node_end_time;
                }

                public double getNode_proportion() {
                    return node_proportion;
                }

                public void setNode_proportion(double node_proportion) {
                    this.node_proportion = node_proportion;
                }

                public double getNode_labor_cost() {
                    return node_labor_cost;
                }

                public void setNode_labor_cost(double node_labor_cost) {
                    this.node_labor_cost = node_labor_cost;
                }

                public double getNode_extras_cost() {
                    return node_extras_cost;
                }

                public void setNode_extras_cost(double node_extras_cost) {
                    this.node_extras_cost = node_extras_cost;
                }

                public List<QuestionsBean> getQuestions() {
                    return questions;
                }

                public void setQuestions(List<QuestionsBean> questions) {
                    this.questions = questions;
                }

                public static class QuestionsBean {
                    /**
                     * id : 1
                     * node_question : 问题111111111111111111111111111111111111111111111111
                     * node_question_answer : null
                     * questiondate : null
                     * answerdate : null
                     */

                    private int id;
                    private String node_question;
                    private String node_question_answer;
                    private String questiondate;
                    private String answerdate;

                    public String getNode_question_answer() {
                        return node_question_answer;
                    }

                    public void setNode_question_answer(String node_question_answer) {
                        this.node_question_answer = node_question_answer;
                    }

                    public String getQuestiondate() {
                        return questiondate;
                    }

                    public void setQuestiondate(String questiondate) {
                        this.questiondate = questiondate;
                    }

                    public String getAnswerdate() {
                        return answerdate;
                    }

                    public void setAnswerdate(String answerdate) {
                        this.answerdate = answerdate;
                    }



                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getNode_question() {
                        return node_question;
                    }

                    public void setNode_question(String node_question) {
                        this.node_question = node_question;
                    }






                }
            }
        }
    }
}
