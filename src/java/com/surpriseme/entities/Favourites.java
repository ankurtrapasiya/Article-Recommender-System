package com.surpriseme.entities;

public class Favourites {

    private Integer userid;
    private Integer articleid;
    private boolean readlater;
    private boolean isfav;

    public boolean isReadlater() {
        return readlater;
    }

    public void setReadlater(boolean readlater) {
        this.readlater = readlater;
    }

    public boolean isIsfav() {
        return isfav;
    }

    @Override
    public String toString() {
        return "Favourites{" + "userid=" + userid + ", articleid=" + articleid + ", readlater=" + readlater + ", isfav=" + isfav + '}';
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

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

    public Favourites(Integer userid, Integer articleid, boolean readlater, boolean isfav) {
        this.userid = userid;
        this.articleid = articleid;
        this.readlater = readlater;
        this.isfav = isfav;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 31 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 31 * hash + (this.readlater ? 1 : 0);
        hash = 31 * hash + (this.isfav ? 1 : 0);
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
        final Favourites other = (Favourites) obj;
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if (this.readlater != other.readlater) {
            return false;
        }
        if (this.isfav != other.isfav) {
            return false;
        }
        return true;
    }
}