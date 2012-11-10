package com.surpriseme.entities;

import java.sql.*;

public class UserActivation {

    private Integer id;
    private Integer userid;
    private String token;
    private Timestamp timestamp;
    private Boolean isactive;

    public UserActivation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public UserActivation(Integer userid, String token, Timestamp timestamp, Boolean isactive) {
        this.userid = userid;
        this.token = token;
        this.timestamp = timestamp;
        this.isactive = isactive;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 97 * hash + (this.token != null ? this.token.hashCode() : 0);
        hash = 97 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        hash = 97 * hash + (this.isactive != null ? this.isactive.hashCode() : 0);
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
        final UserActivation other = (UserActivation) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if ((this.token == null) ? (other.token != null) : !this.token.equals(other.token)) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        if (this.isactive != other.isactive && (this.isactive == null || !this.isactive.equals(other.isactive))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserActivation{" + "id=" + id + ", userid=" + userid + ", token=" + token + ", timestamp=" + timestamp + ", isactive=" + isactive + '}';
    }
    
    
}
