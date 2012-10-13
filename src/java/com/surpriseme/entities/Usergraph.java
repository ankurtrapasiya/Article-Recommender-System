/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "usergraph", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usergraph.findAll", query = "SELECT u FROM Usergraph u"),
    @NamedQuery(name = "Usergraph.findByFriendid", query = "SELECT u FROM Usergraph u WHERE u.usergraphPK.friendid = :friendid"),
    @NamedQuery(name = "Usergraph.findByUserid", query = "SELECT u FROM Usergraph u WHERE u.usergraphPK.userid = :userid")})
public class Usergraph implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsergraphPK usergraphPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendid", fetch = FetchType.EAGER)
    private List<Usersuggestions> usersuggestionsList;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(name = "friendid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user1;

    public Usergraph() {
    }

    public Usergraph(UsergraphPK usergraphPK) {
        this.usergraphPK = usergraphPK;
    }

    public Usergraph(int friendid, int userid) {
        this.usergraphPK = new UsergraphPK(friendid, userid);
    }

    public UsergraphPK getUsergraphPK() {
        return usergraphPK;
    }

    public void setUsergraphPK(UsergraphPK usergraphPK) {
        this.usergraphPK = usergraphPK;
    }

    @XmlTransient
    public List<Usersuggestions> getUsersuggestionsList() {
        return usersuggestionsList;
    }

    public void setUsersuggestionsList(List<Usersuggestions> usersuggestionsList) {
        this.usersuggestionsList = usersuggestionsList;
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
        hash += (usergraphPK != null ? usergraphPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usergraph)) {
            return false;
        }
        Usergraph other = (Usergraph) object;
        if ((this.usergraphPK == null && other.usergraphPK != null) || (this.usergraphPK != null && !this.usergraphPK.equals(other.usergraphPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Usergraph[ usergraphPK=" + usergraphPK + " ]";
    }
    
}
