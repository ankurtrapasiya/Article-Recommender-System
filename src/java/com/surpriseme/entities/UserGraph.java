package com.surpriseme.entities;

public class UserGraph {

    private Integer friendid;
    private Integer userid;
    private Integer isnotified;

    public Integer getIsnotified() {
        return isnotified;
    }

    public void setIsnotified(Integer isnotified) {
        this.isnotified = isnotified;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}