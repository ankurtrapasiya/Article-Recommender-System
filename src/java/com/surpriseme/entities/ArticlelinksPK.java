/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ankur
 */
@Embeddable
public class ArticlelinksPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "articleid", nullable = false)
    private int articleid;
    @Basic(optional = false)
    @Column(name = "sourceid", nullable = false)
    private int sourceid;

    public ArticlelinksPK() {
    }

    public ArticlelinksPK(int articleid, int sourceid) {
        this.articleid = articleid;
        this.sourceid = sourceid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) articleid;
        hash += (int) sourceid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticlelinksPK)) {
            return false;
        }
        ArticlelinksPK other = (ArticlelinksPK) object;
        if (this.articleid != other.articleid) {
            return false;
        }
        if (this.sourceid != other.sourceid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.ArticlelinksPK[ articleid=" + articleid + ", sourceid=" + sourceid + " ]";
    }
    
}
