package com.surpriseme.entities;

public class InterestSources {

    private Integer interestid;
    private Integer sourceid;

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    @Override
    public String toString() {
        return "InterestSources{" + "interestid=" + interestid + ", sourceid=" + sourceid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.interestid != null ? this.interestid.hashCode() : 0);
        hash = 23 * hash + (this.sourceid != null ? this.sourceid.hashCode() : 0);
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
        final InterestSources other = (InterestSources) obj;
        if (this.interestid != other.interestid && (this.interestid == null || !this.interestid.equals(other.interestid))) {
            return false;
        }
        if (this.sourceid != other.sourceid && (this.sourceid == null || !this.sourceid.equals(other.sourceid))) {
            return false;
        }
        return true;
    }
}