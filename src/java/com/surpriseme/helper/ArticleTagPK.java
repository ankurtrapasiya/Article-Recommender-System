/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.helper;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ankur
 */
public class ArticleTagPK implements Serializable {

    private Integer articleId;
    private Integer tagId;

    public ArticleTagPK(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.articleId != null ? this.articleId.hashCode() : 0);
        hash = 67 * hash + (this.tagId != null ? this.tagId.hashCode() : 0);
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
        final ArticleTagPK other = (ArticleTagPK) obj;
        if (this.articleId != other.articleId && (this.articleId == null || !this.articleId.equals(other.articleId))) {
            return false;
        }
        if (this.tagId != other.tagId && (this.tagId == null || !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }
}
