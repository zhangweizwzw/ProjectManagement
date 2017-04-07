package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/7.
 */

public class Two {
    private String name;

    public List<Three> getTlist() {
        return tlist;
    }

    public void setTlist(List<Three> tlist) {
        this.tlist = tlist;
    }

    private String age;
    private List<Three> tlist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
