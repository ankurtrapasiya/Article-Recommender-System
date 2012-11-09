package com.surpriseme.entities;

public class TagInterest {

    private Integer tagid;
    private Integer interestid;

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.tagid != null ? this.tagid.hashCode() : 0);
        hash = 17 * hash + (this.interestid != null ? this.interestid.hashCode() : 0);
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
        final TagInterest other = (TagInterest) obj;
        if (this.tagid != other.tagid && (this.tagid == null || !this.tagid.equals(other.tagid))) {
            return false;
        }
        if (this.interestid != other.interestid && (this.interestid == null || !this.interestid.equals(other.interestid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TagInterest{" + "tagid=" + tagid + ", interestid=" + interestid + '}';
    }
    
    
    
}
