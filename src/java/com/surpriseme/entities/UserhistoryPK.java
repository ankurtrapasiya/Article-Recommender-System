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
public class UserhistoryPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Userid", nullable = false)
    private int userid;
    @Basic(optional = false)
    @Column(name = "articleid", nullable = false)
    private int articleid;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public UserhistoryPK() {
    }

    public UserhistoryPK(int userid, int articleid, Date timestamp) {
        this.userid = userid;
        this.articleid = articleid;
        this.timestamp = timestamp;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
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
        hash += (int) userid;
        hash += (int) articleid;
        hash += (timestamp != null ? timestamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserhistoryPK)) {
            return false;
        }
        UserhistoryPK other = (UserhistoryPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.articleid != other.articleid) {
            return false;
        }
        if ((this.timestamp == null && other.timestamp != null) || (this.timestamp != null && !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.UserhistoryPK[ userid=" + userid + ", articleid=" + articleid + ", timestamp=" + timestamp + " ]";
    }
    
}
