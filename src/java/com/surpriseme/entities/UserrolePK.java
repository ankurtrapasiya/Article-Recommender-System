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
public class UserrolePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "rolename", nullable = false, length = 20)
    private String rolename;
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private int userid;

    public UserrolePK() {
    }

    public UserrolePK(String rolename, int userid) {
        this.rolename = rolename;
        this.userid = userid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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
        hash += (rolename != null ? rolename.hashCode() : 0);
        hash += (int) userid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserrolePK)) {
            return false;
        }
        UserrolePK other = (UserrolePK) object;
        if ((this.rolename == null && other.rolename != null) || (this.rolename != null && !this.rolename.equals(other.rolename))) {
            return false;
        }
        if (this.userid != other.userid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.UserrolePK[ rolename=" + rolename + ", userid=" + userid + " ]";
    }
    
}
