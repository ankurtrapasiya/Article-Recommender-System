package com.surpriseme.entities;

import java.sql.*;

public class UserHistory {

    private Integer Userid;
    private Integer articleid;
    private Timestamp timestamp;
    private Boolean upvote;
    private Boolean downvote;

    public Integer getUserid() {
        return Userid;
    }

    public void setUserid(Integer Userid) {
        this.Userid = Userid;
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

    public UserHistory(Integer Userid, Integer articleid, Timestamp timestamp, Boolean upvote, Boolean downvote) {
        this.Userid = Userid;
        this.articleid = articleid;
        this.timestamp = timestamp;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public UserHistory() {
    }
}