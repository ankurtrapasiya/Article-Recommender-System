package com.surpriseme.entities;

import java.sql.*;

public class UserSuggestions {

    private Integer id;
    private Integer userid;
    private Integer friendid;
    private Integer articleid;
    private Boolean isviewed;
    private Timestamp timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Boolean getIsviewed() {
        return isviewed;
    }

    public void setIsviewed(Boolean isviewed) {
        this.isviewed = isviewed;
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
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 53 * hash + (this.friendid != null ? this.friendid.hashCode() : 0);
        hash = 53 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 53 * hash + (this.isviewed != null ? this.isviewed.hashCode() : 0);
        hash = 53 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
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
        final UserSuggestions other = (UserSuggestions) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.friendid != other.friendid && (this.friendid == null || !this.friendid.equals(other.friendid))) {
            return false;
        }
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if (this.isviewed != other.isviewed && (this.isviewed == null || !this.isviewed.equals(other.isviewed))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserSuggestions{" + "id=" + id + ", userid=" + userid + ", friendid=" + friendid + ", articleid=" + articleid + ", isviewed=" + isviewed + ", timestamp=" + timestamp + '}';
    }
    
    
    
}