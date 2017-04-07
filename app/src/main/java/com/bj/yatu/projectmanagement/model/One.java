package com.bj.yatu.projectmanagement.model;

import java.util.List;

/**
 * Created by admin on 2017/4/7.
 */

public class One {
    private String id;
    private String name;

    public List<Two> getLlist() {
        return llist;
    }

    public void setLlist(List<Two> llist) {
        this.llist = llist;
    }

    private List<Two> llist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
