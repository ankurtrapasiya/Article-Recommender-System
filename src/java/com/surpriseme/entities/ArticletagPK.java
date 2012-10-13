/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ankur
 */
@Embeddable
public class ArticletagPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "articleid", nullable = false)
    private int articleid;
    @Basic(optional = false)
    @Column(name = "tagid", nullable = false)
    private int tagid;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public ArticletagPK() {
    }

    public ArticletagPK(int articleid, int tagid, Date timestamp) {
        this.articleid = articleid;
        this.tagid = tagid;
        this.timestamp = timestamp;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) articleid;
        hash += (int) tagid;
        hash += (timestamp != null ? timestamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticletagPK)) {
            return false;
        }
        ArticletagPK other = (ArticletagPK) object;
        if (this.articleid != other.articleid) {
            return false;
        }
        if (this.tagid != other.tagid) {
            return false;
        }
        if ((this.timestamp == null && other.timestamp != null) || (this.timestamp != null && !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.ArticletagPK[ articleid=" + articleid + ", tagid=" + tagid + ", timestamp=" + timestamp + " ]";
    }
    
}
