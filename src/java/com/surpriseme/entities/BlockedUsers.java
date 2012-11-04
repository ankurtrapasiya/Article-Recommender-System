package com.surpriseme.entities;

import java.sql.*;

public class BlockedUsers {

    private Integer userid;
    private Integer blockerid;
    private Timestamp timestamp;
    private String reason;
    private boolean isActive;

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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 17 * hash + (this.blockerid != null ? this.blockerid.hashCode() : 0);
        hash = 17 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        hash = 17 * hash + (this.reason != null ? this.reason.hashCode() : 0);
        hash = 17 * hash + (this.isActive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlockedUsers other = (BlockedUsers) obj;
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.blockerid != other.blockerid && (this.blockerid == null || !this.blockerid.equals(other.blockerid))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        if ((this.reason == null) ? (other.reason != null) : !this.reason.equals(other.reason)) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlockedUsers{" + "userid=" + userid + ", blockerid=" + blockerid + ", timestamp=" + timestamp + ", reason=" + reason + ", isActive=" + isActive + '}';
    }
    
}