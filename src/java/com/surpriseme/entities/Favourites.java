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
}