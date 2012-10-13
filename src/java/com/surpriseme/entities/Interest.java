/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "interest", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i"),
    @NamedQuery(name = "Interest.findByInterestid", query = "SELECT i FROM Interest i WHERE i.interestid = :interestid"),
    @NamedQuery(name = "Interest.findByName", query = "SELECT i FROM Interest i WHERE i.name = :name"),
    @NamedQuery(name = "Interest.findByDescription", query = "SELECT i FROM Interest i WHERE i.description = :description"),
    @NamedQuery(name = "Interest.findByIconpath", query = "SELECT i FROM Interest i WHERE i.iconpath = :iconpath"),
    @NamedQuery(name = "Interest.findByTimestamp", query = "SELECT i FROM Interest i WHERE i.timestamp = :timestamp")})
public class Interest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "interestid", nullable = false)
    private Integer interestid;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(name = "iconpath", nullable = false, length = 200)
    private String iconpath;
    @Basic(optional = false)
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinTable(name = "userinterest", joinColumns = {
        @JoinColumn(name = "interestid", referencedColumnName = "interestid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;
    @ManyToMany(mappedBy = "interestList", fetch = FetchType.EAGER)
    private List<Tag> tagList;
    @JoinTable(name = "interestsources", joinColumns = {
        @JoinColumn(name = "interestid", referencedColumnName = "interestid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "sourceid", referencedColumnName = "sourceid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Source> sourceList;
    @ManyToMany(mappedBy = "interestList", fetch = FetchType.EAGER)
    private List<Article> articleList;

    public Interest() {
    }

    public Interest(Integer interestid) {
        this.interestid = interestid;
    }

    public Interest(Integer interestid, String name, String description, String iconpath, Date timestamp) {
        this.interestid = interestid;
        this.name = name;
        this.description = description;
        this.iconpath = iconpath;
        this.timestamp = timestamp;
    }

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
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
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @XmlTransient
    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interestid != null ? interestid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.interestid == null && other.interestid != null) || (this.interestid != null && !this.interestid.equals(other.interestid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Interest[ interestid=" + interestid + " ]";
    }
    
}
