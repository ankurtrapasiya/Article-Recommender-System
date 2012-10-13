/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "userrole", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u"),
    @NamedQuery(name = "Userrole.findByRolename", query = "SELECT u FROM Userrole u WHERE u.userrolePK.rolename = :rolename"),
    @NamedQuery(name = "Userrole.findByUserid", query = "SELECT u FROM Userrole u WHERE u.userrolePK.userid = :userid"),
    @NamedQuery(name = "Userrole.findByUsername", query = "SELECT u FROM Userrole u WHERE u.username = :username")})
public class Userrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserrolePK userrolePK;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(name = "rolename", referencedColumnName = "rolename", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Role role;

    public Userrole() {
    }

    public Userrole(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    public Userrole(UserrolePK userrolePK, String username) {
        this.userrolePK = userrolePK;
        this.username = username;
    }

    public Userrole(String rolename, int userid) {
        this.userrolePK = new UserrolePK(rolename, userid);
    }

    public UserrolePK getUserrolePK() {
        return userrolePK;
    }

    public void setUserrolePK(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userrolePK != null ? userrolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.userrolePK == null && other.userrolePK != null) || (this.userrolePK != null && !this.userrolePK.equals(other.userrolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Userrole[ userrolePK=" + userrolePK + " ]";
    }
    
}
