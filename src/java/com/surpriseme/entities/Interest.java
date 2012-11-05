package com.surpriseme.entities;

import java.sql.*;

public class Interest {

    private Integer interestid;
    private String name;
    private String description;
    private String iconpath;
    private Timestamp timestamp;

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}