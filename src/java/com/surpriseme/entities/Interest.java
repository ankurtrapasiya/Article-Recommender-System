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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.interestid != null ? this.interestid.hashCode() : 0);
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 13 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 13 * hash + (this.iconpath != null ? this.iconpath.hashCode() : 0);
        hash = 13 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
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
        final Interest other = (Interest) obj;
        if (this.interestid != other.interestid && (this.interestid == null || !this.interestid.equals(other.interestid))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.iconpath == null) ? (other.iconpath != null) : !this.iconpath.equals(other.iconpath)) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interest{" + "interestid=" + interestid + ", name=" + name + ", description=" + description + ", iconpath=" + iconpath + ", timestamp=" + timestamp + '}';
    }
}