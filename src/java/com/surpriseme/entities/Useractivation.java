/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "useractivation", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useractivation.findAll", query = "SELECT u FROM Useractivation u"),
    @NamedQuery(name = "Useractivation.findById", query = "SELECT u FROM Useractivation u WHERE u.id = :id"),
    @NamedQuery(name = "Useractivation.findByToken", query = "SELECT u FROM Useractivation u WHERE u.token = :token"),
    @NamedQuery(name = "Useractivation.findByTimestamp", query = "SELECT u FROM Useractivation u WHERE u.timestamp = :timestamp"),
    @NamedQuery(name = "Useractivation.findByIsactive", query = "SELECT u FROM Useractivation u WHERE u.isactive = :isactive")})
public class Useractivation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "token", nullable = false, length = 255)
    private String token;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userid;

    public Useractivation() {
    }

    public Useractivation(Integer id) {
        this.id = id;
    }

    public Useractivation(Integer id, String token, Date timestamp, boolean isactive) {
        this.id = id;
        this.token = token;
        this.timestamp = timestamp;
        this.isactive = isactive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useractivation)) {
            return false;
        }
        Useractivation other = (Useractivation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Useractivation[ id=" + id + " ]";
    }
    
}
