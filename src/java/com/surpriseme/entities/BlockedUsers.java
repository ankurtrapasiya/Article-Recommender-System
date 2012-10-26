package com.surpriseme.entities;

import java.sql.*;

public class BlockedUsers {

    private Integer userid;
    private Integer blockerid;
    private Timestamp timestamp;
    private String reason;
    private boolean isActive;
    private String username;
    private String blockername;

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlockername() {
        return blockername;
    }

    public void setBlockername(String blockername) {
        this.blockername = blockername;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}