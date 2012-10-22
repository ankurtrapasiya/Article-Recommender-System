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
@Table(name = "usersuggestions", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usersuggestions.findAll", query = "SELECT u FROM Usersuggestions u"),
    @NamedQuery(name = "Usersuggestions.findById", query = "SELECT u FROM Usersuggestions u WHERE u.id = :id"),
    @NamedQuery(name = "Usersuggestions.findByIsviewed", query = "SELECT u FROM Usersuggestions u WHERE u.isviewed = :isviewed"),
    @NamedQuery(name = "Usersuggestions.findByTimestamp", query = "SELECT u FROM Usersuggestions u WHERE u.timestamp = :timestamp")})
public class Usersuggestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "isviewed", nullable = false)
    private boolean isviewed;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userid;
    @JoinColumn(name = "friendid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User friendid;
    @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Article articleid;

    public Usersuggestions() {
    }

    public Usersuggestions(Integer id) {
        this.id = id;
    }

    public Usersuggestions(Integer id, boolean isviewed, Date timestamp) {
        this.id = id;
        this.isviewed = isviewed;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getIsviewed() {
        return isviewed;
    }

    public void setIsviewed(boolean isviewed) {
        this.isviewed = isviewed;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public User getFriendid() {
        return friendid;
    }

    public void setFriendid(User friendid) {
        this.friendid = friendid;
    }

    public Article getArticleid() {
        return articleid;
    }

    public void setArticleid(Article articleid) {
        this.articleid = articleid;
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
        if (!(object instanceof Usersuggestions)) {
            return false;
        }
        Usersuggestions other = (Usersuggestions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Usersuggestions[ id=" + id + " ]";
    }
    
}
