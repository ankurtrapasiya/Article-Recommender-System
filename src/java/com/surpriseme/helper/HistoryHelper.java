/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.helper;

import java.sql.Timestamp;

/**
 *
 * @author Vikr@m
 */
public class HistoryHelper {

    private String title;
    private Timestamp timestamp;
    private Boolean upvote;
    private Boolean downvote;
    private Integer articleid;

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
