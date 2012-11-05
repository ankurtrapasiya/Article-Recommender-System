package com.surpriseme.entities;

import java.sql.*;

public class Article {

    private Integer articleid;
    private String title;
    private String body;
    private Integer upvote=0;
    private Integer downvote=0;
    private Integer viewed=0;
    private Timestamp timestamp;
    private Float popularityscore=0.0f;
    private Timestamp publicationdate;

    public Float getPopularityscore() {
        return popularityscore;
    }

    public Timestamp getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(Timestamp publicationdate) {
        this.publicationdate = publicationdate;
    }

    public void setPopularityscore(Float popularityscore) {
        this.popularityscore = popularityscore;
    }

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUpvote() {
        return upvote;
    }

    public void setUpvote(Integer upvote) {
        this.upvote = upvote;
    }

    public Integer getDownvote() {
        return downvote;
    }

    public void setDownvote(Integer downvote) {
        this.downvote = downvote;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}