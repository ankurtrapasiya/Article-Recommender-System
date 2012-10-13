/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "article", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticleid", query = "SELECT a FROM Article a WHERE a.articleid = :articleid"),
    @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title"),
    @NamedQuery(name = "Article.findByBody", query = "SELECT a FROM Article a WHERE a.body = :body"),
    @NamedQuery(name = "Article.findByUpvote", query = "SELECT a FROM Article a WHERE a.upvote = :upvote"),
    @NamedQuery(name = "Article.findByDownvote", query = "SELECT a FROM Article a WHERE a.downvote = :downvote"),
    @NamedQuery(name = "Article.findByViewed", query = "SELECT a FROM Article a WHERE a.viewed = :viewed"),
    @NamedQuery(name = "Article.findByTimestamp", query = "SELECT a FROM Article a WHERE a.timestamp = :timestamp")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "articleid", nullable = false)
    private Integer articleid;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Basic(optional = false)
    @Column(name = "body", nullable = false, length = 65000)
    private String body;
    @Basic(optional = false)
    @Column(name = "upvote", nullable = false)
    private int upvote;
    @Basic(optional = false)
    @Column(name = "downvote", nullable = false)
    private int downvote;
    @Basic(optional = false)
    @Column(name = "viewed", nullable = false)
    private int viewed;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @ManyToMany(mappedBy = "articleList", fetch = FetchType.EAGER)
    private List<User> userList;
    @JoinTable(name = "articleinterest", joinColumns = {
        @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "interestid", referencedColumnName = "interestid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Interest> interestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
    private List<Articlelinks> articlelinksList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
    private List<Articletag> articletagList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleid", fetch = FetchType.EAGER)
    private List<Usersuggestions> usersuggestionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
    private List<Userhistory> userhistoryList;

    public Article() {
    }

    public Article(Integer articleid) {
        this.articleid = articleid;
    }

    public Article(Integer articleid, String title, String body, int upvote, int downvote, int viewed, Date timestamp) {
        this.articleid = articleid;
        this.title = title;
        this.body = body;
        this.upvote = upvote;
        this.downvote = downvote;
        this.viewed = viewed;
        this.timestamp = timestamp;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    @XmlTransient
    public List<Articlelinks> getArticlelinksList() {
        return articlelinksList;
    }

    public void setArticlelinksList(List<Articlelinks> articlelinksList) {
        this.articlelinksList = articlelinksList;
    }

    @XmlTransient
    public List<Articletag> getArticletagList() {
        return articletagList;
    }

    public void setArticletagList(List<Articletag> articletagList) {
        this.articletagList = articletagList;
    }

    @XmlTransient
    public List<Usersuggestions> getUsersuggestionsList() {
        return usersuggestionsList;
    }

    public void setUsersuggestionsList(List<Usersuggestions> usersuggestionsList) {
        this.usersuggestionsList = usersuggestionsList;
    }

    @XmlTransient
    public List<Userhistory> getUserhistoryList() {
        return userhistoryList;
    }

    public void setUserhistoryList(List<Userhistory> userhistoryList) {
        this.userhistoryList = userhistoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleid != null ? articleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleid == null && other.articleid != null) || (this.articleid != null && !this.articleid.equals(other.articleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Article[ articleid=" + articleid + " ]";
    }
    
}
