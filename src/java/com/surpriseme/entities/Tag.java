/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "tag", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByTagid", query = "SELECT t FROM Tag t WHERE t.tagid = :tagid"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name"),
    @NamedQuery(name = "Tag.findByIcon", query = "SELECT t FROM Tag t WHERE t.icon = :icon"),
    @NamedQuery(name = "Tag.findByDescription", query = "SELECT t FROM Tag t WHERE t.description = :description")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tagid", nullable = false)
    private Integer tagid;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "icon", nullable = false, length = 200)
    private String icon;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @JoinTable(name = "taginterest", joinColumns = {
        @JoinColumn(name = "tagid", referencedColumnName = "tagid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "interestid", referencedColumnName = "interestid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Interest> interestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag", fetch = FetchType.EAGER)
    private List<Articletag> articletagList;

    public Tag() {
    }

    public Tag(Integer tagid) {
        this.tagid = tagid;
    }

    public Tag(Integer tagid, String name, String icon, String description) {
        this.tagid = tagid;
        this.name = name;
        this.icon = icon;
        this.description = description;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    @XmlTransient
    public List<Articletag> getArticletagList() {
        return articletagList;
    }

    public void setArticletagList(List<Articletag> articletagList) {
        this.articletagList = articletagList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagid != null ? tagid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagid == null && other.tagid != null) || (this.tagid != null && !this.tagid.equals(other.tagid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Tag[ tagid=" + tagid + " ]";
    }
    
}
