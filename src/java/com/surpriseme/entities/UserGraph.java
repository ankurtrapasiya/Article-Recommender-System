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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.friendid != null ? this.friendid.hashCode() : 0);
        hash = 11 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 11 * hash + (this.isnotified != null ? this.isnotified.hashCode() : 0);
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
        final UserGraph other = (UserGraph) obj;
        if (this.friendid != other.friendid && (this.friendid == null || !this.friendid.equals(other.friendid))) {
            return false;
        }
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.isnotified != other.isnotified && (this.isnotified == null || !this.isnotified.equals(other.isnotified))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserGraph{" + "friendid=" + friendid + ", userid=" + userid + ", isnotified=" + isnotified + '}';
    }
}