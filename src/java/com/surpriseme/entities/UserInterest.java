package com.surpriseme.entities;

public class UserInterest {

    private Integer userid;
    private Integer interestid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 97 * hash + (this.interestid != null ? this.interestid.hashCode() : 0);
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
        final UserInterest other = (UserInterest) obj;
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.interestid != other.interestid && (this.interestid == null || !this.interestid.equals(other.interestid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserInterest{" + "userid=" + userid + ", interestid=" + interestid + '}';
    }
}
