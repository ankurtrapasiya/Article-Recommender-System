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
@Table(name = "source", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Source.findAll", query = "SELECT s FROM Source s"),
    @NamedQuery(name = "Source.findBySourceid", query = "SELECT s FROM Source s WHERE s.sourceid = :sourceid"),
    @NamedQuery(name = "Source.findByName", query = "SELECT s FROM Source s WHERE s.name = :name"),
    @NamedQuery(name = "Source.findByUrl", query = "SELECT s FROM Source s WHERE s.url = :url"),
    @NamedQuery(name = "Source.findByFeedurl", query = "SELECT s FROM Source s WHERE s.feedurl = :feedurl"),
    @NamedQuery(name = "Source.findByIcon", query = "SELECT s FROM Source s WHERE s.icon = :icon"),
    @NamedQuery(name = "Source.findByIsactive", query = "SELECT s FROM Source s WHERE s.isactive = :isactive")})
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sourceid", nullable = false)
    private Integer sourceid;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "url", nullable = false, length = 200)
    private String url;
    @Basic(optional = false)
    @Column(name = "feedurl", nullable = false, length = 200)
    private String feedurl;
    @Basic(optional = false)
    @Column(name = "icon", nullable = false, length = 200)
    private String icon;
    @Basic(optional = false)
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @ManyToMany(mappedBy = "sourceList", fetch = FetchType.EAGER)
    private List<Interest> interestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source", fetch = FetchType.EAGER)
    private List<Articlelinks> articlelinksList;

    public Source() {
    }

    public Source(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Source(Integer sourceid, String name, String url, String feedurl, String icon, boolean isactive) {
        this.sourceid = sourceid;
        this.name = name;
        this.url = url;
        this.feedurl = feedurl;
        this.icon = icon;
        this.isactive = isactive;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFeedurl() {
        return feedurl;
    }

    public void setFeedurl(String feedurl) {
        this.feedurl = feedurl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sourceid != null ? sourceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Source)) {
            return false;
        }
        Source other = (Source) object;
        if ((this.sourceid == null && other.sourceid != null) || (this.sourceid != null && !this.sourceid.equals(other.sourceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Source[ sourceid=" + sourceid + " ]";
    }
    
}
