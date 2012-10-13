/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "userhistory", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userhistory.findAll", query = "SELECT u FROM Userhistory u"),
    @NamedQuery(name = "Userhistory.findByUserid", query = "SELECT u FROM Userhistory u WHERE u.userhistoryPK.userid = :userid"),
    @NamedQuery(name = "Userhistory.findByArticleid", query = "SELECT u FROM Userhistory u WHERE u.userhistoryPK.articleid = :articleid"),
    @NamedQuery(name = "Userhistory.findByTimestamp", query = "SELECT u FROM Userhistory u WHERE u.userhistoryPK.timestamp = :timestamp"),
    @NamedQuery(name = "Userhistory.findByUpvote", query = "SELECT u FROM Userhistory u WHERE u.upvote = :upvote"),
    @NamedQuery(name = "Userhistory.findByDownvote", query = "SELECT u FROM Userhistory u WHERE u.downvote = :downvote")})
public class Userhistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserhistoryPK userhistoryPK;
    @Basic(optional = false)
    @Column(name = "upvote", nullable = false)
    private boolean upvote;
    @Basic(optional = false)
    @Column(name = "downvote", nullable = false)
    private boolean downvote;
    @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Article article;
    @JoinColumn(name = "Userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public Userhistory() {
    }

    public Userhistory(UserhistoryPK userhistoryPK) {
        this.userhistoryPK = userhistoryPK;
    }

    public Userhistory(UserhistoryPK userhistoryPK, boolean upvote, boolean downvote) {
        this.userhistoryPK = userhistoryPK;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Userhistory(int userid, int articleid, Date timestamp) {
        this.userhistoryPK = new UserhistoryPK(userid, articleid, timestamp);
    }

    public UserhistoryPK getUserhistoryPK() {
        return userhistoryPK;
    }

    public void setUserhistoryPK(UserhistoryPK userhistoryPK) {
        this.userhistoryPK = userhistoryPK;
    }

    public boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

    public boolean getDownvote() {
        return downvote;
    }

    public void setDownvote(boolean downvote) {
        this.downvote = downvote;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userhistoryPK != null ? userhistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userhistory)) {
            return false;
        }
        Userhistory other = (Userhistory) object;
        if ((this.userhistoryPK == null && other.userhistoryPK != null) || (this.userhistoryPK != null && !this.userhistoryPK.equals(other.userhistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Userhistory[ userhistoryPK=" + userhistoryPK + " ]";
    }
    
}
