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
public class UsergraphPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "friendid", nullable = false)
    private int friendid;
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private int userid;

    public UsergraphPK() {
    }

    public UsergraphPK(int friendid, int userid) {
        this.friendid = friendid;
        this.userid = userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) friendid;
        hash += (int) userid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsergraphPK)) {
            return false;
        }
        UsergraphPK other = (UsergraphPK) object;
        if (this.friendid != other.friendid) {
            return false;
        }
        if (this.userid != other.userid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.UsergraphPK[ friendid=" + friendid + ", userid=" + userid + " ]";
    }
    
}
