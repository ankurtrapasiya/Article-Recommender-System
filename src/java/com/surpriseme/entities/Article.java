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

    @Override
    public String toString() {
        return "Article{" + "articleid=" + articleid + ", title=" + title + ", body=" + body + ", upvote=" + upvote + ", downvote=" + downvote + ", viewed=" + viewed + ", timestamp=" + timestamp + ", popularityscore=" + popularityscore + ", publicationdate=" + publicationdate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Article other = (Article) obj;
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.body == null) ? (other.body != null) : !this.body.equals(other.body)) {
            return false;
        }
        if (this.upvote != other.upvote && (this.upvote == null || !this.upvote.equals(other.upvote))) {
            return false;
        }
        if (this.downvote != other.downvote && (this.downvote == null || !this.downvote.equals(other.downvote))) {
            return false;
        }
        if (this.viewed != other.viewed && (this.viewed == null || !this.viewed.equals(other.viewed))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        if (this.popularityscore != other.popularityscore && (this.popularityscore == null || !this.popularityscore.equals(other.popularityscore))) {
            return false;
        }
        if (this.publicationdate != other.publicationdate && (this.publicationdate == null || !this.publicationdate.equals(other.publicationdate))) {
            return false;
        }
        return true;
    }
}