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
}