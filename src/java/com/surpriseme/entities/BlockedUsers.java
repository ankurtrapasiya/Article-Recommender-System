package com.surpriseme.entities;

import java.sql.*;

public class BlockedUsers{

    private Integer userid;
    private Integer blockerid;
    private Timestamp timestamp;
    private String reason;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBlockerid() {
        return blockerid;
    }

    public void setBlockerid(Integer blockerid) {
        this.blockerid = blockerid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}