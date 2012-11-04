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
}