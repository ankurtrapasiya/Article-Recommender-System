package com.surpriseme.entities;

import java.sql.*;

public class UserHistory {

    private Integer userid;
    private Integer articleid;
    private Timestamp timestamp;
    private Boolean upvote;
    private Boolean downvote;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }

    public Boolean getDownvote() {
        return downvote;
    }

    public void setDownvote(Boolean downvote) {
        this.downvote = downvote;
    }

    public UserHistory(Integer userid, Integer articleid, Timestamp timestamp, Boolean upvote, Boolean downvote) {
        this.userid = userid;
        this.articleid = articleid;
        this.timestamp = timestamp;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public UserHistory() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 59 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 59 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        hash = 59 * hash + (this.upvote != null ? this.upvote.hashCode() : 0);
        hash = 59 * hash + (this.downvote != null ? this.downvote.hashCode() : 0);
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
        final UserHistory other = (UserHistory) obj;
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        if (this.upvote != other.upvote && (this.upvote == null || !this.upvote.equals(other.upvote))) {
            return false;
        }
        if (this.downvote != other.downvote && (this.downvote == null || !this.downvote.equals(other.downvote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserHistory{" + "userid=" + userid + ", articleid=" + articleid + ", timestamp=" + timestamp + ", upvote=" + upvote + ", downvote=" + downvote + '}';
    }
}