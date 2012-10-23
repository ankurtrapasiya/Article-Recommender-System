package com.surpriseme.entities;

import java.sql.*;
import java.util.*;

public class ArticleLinks {

    private Integer articleid;
    private String articleurl;
    private Integer sourceid;

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }
}