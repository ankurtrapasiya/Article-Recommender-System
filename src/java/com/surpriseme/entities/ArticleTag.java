package com.surpriseme.entities;

import java.sql.*;
import java.util.*;

public class ArticleTag {

    private Integer articleid;
    private Integer tagid;
    private Timestamp timestamp;

    public ArticleTag(Integer articleid, Integer tagid, Timestamp timestamp) {
        this.articleid = articleid;
        this.tagid = tagid;
        this.timestamp = timestamp;
    }

    
    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ArticleTag{" + "articleid=" + articleid + ", tagid=" + tagid + ", timestamp=" + timestamp + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 11 * hash + (this.tagid != null ? this.tagid.hashCode() : 0);
        hash = 11 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
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
        final ArticleTag other = (ArticleTag) obj;
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if (this.tagid != other.tagid && (this.tagid == null || !this.tagid.equals(other.tagid))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }
}
