/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "blockedusers", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blockedusers.findAll", query = "SELECT b FROM Blockedusers b"),
    @NamedQuery(name = "Blockedusers.findByUserid", query = "SELECT b FROM Blockedusers b WHERE b.blockedusersPK.userid = :userid"),
    @NamedQuery(name = "Blockedusers.findByBlockerid", query = "SELECT b FROM Blockedusers b WHERE b.blockedusersPK.blockerid = :blockerid"),
    @NamedQuery(name = "Blockedusers.findByTimestamp", query = "SELECT b FROM Blockedusers b WHERE b.blockedusersPK.timestamp = :timestamp"),
    @NamedQuery(name = "Blockedusers.findByReason", query = "SELECT b FROM Blockedusers b WHERE b.reason = :reason")})
public class Blockedusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BlockedusersPK blockedusersPK;
    @Column(name = "reason", length = 200)
    private String reason;
    @JoinColumn(name = "blockerid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user1;

    public Blockedusers() {
    }

    public Blockedusers(BlockedusersPK blockedusersPK) {
        this.blockedusersPK = blockedusersPK;
    }

    public Blockedusers(int userid, int blockerid, Date timestamp) {
        this.blockedusersPK = new BlockedusersPK(userid, blockerid, timestamp);
    }

    public BlockedusersPK getBlockedusersPK() {
        return blockedusersPK;
    }

    public void setBlockedusersPK(BlockedusersPK blockedusersPK) {
        this.blockedusersPK = blockedusersPK;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blockedusersPK != null ? blockedusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blockedusers)) {
            return false;
        }
        Blockedusers other = (Blockedusers) object;
        if ((this.blockedusersPK == null && other.blockedusersPK != null) || (this.blockedusersPK != null && !this.blockedusersPK.equals(other.blockedusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Blockedusers[ blockedusersPK=" + blockedusersPK + " ]";
    }
    
}
