package com.surpriseme.entities;

import java.sql.*;

public class UserGraph {

    private Integer friendid;
    private Integer userid;

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