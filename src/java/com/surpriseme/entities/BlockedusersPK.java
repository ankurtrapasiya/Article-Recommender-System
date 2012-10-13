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
public class BlockedusersPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private int userid;
    @Basic(optional = false)
    @Column(name = "blockerid", nullable = false)
    private int blockerid;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public BlockedusersPK() {
    }

    public BlockedusersPK(int userid, int blockerid, Date timestamp) {
        this.userid = userid;
        this.blockerid = blockerid;
        this.timestamp = timestamp;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBlockerid() {
        return blockerid;
    }

    public void setBlockerid(int blockerid) {
        this.blockerid = blockerid;
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
        hash += (int) blockerid;
        hash += (timestamp != null ? timestamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockedusersPK)) {
            return false;
        }
        BlockedusersPK other = (BlockedusersPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.blockerid != other.blockerid) {
            return false;
        }
        if ((this.timestamp == null && other.timestamp != null) || (this.timestamp != null && !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.BlockedusersPK[ userid=" + userid + ", blockerid=" + blockerid + ", timestamp=" + timestamp + " ]";
    }
    
}
