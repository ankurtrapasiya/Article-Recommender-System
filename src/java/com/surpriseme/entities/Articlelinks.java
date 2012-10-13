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
@Table(name = "articlelinks", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articlelinks.findAll", query = "SELECT a FROM Articlelinks a"),
    @NamedQuery(name = "Articlelinks.findByArticleid", query = "SELECT a FROM Articlelinks a WHERE a.articlelinksPK.articleid = :articleid"),
    @NamedQuery(name = "Articlelinks.findByArticleurl", query = "SELECT a FROM Articlelinks a WHERE a.articleurl = :articleurl"),
    @NamedQuery(name = "Articlelinks.findBySourceid", query = "SELECT a FROM Articlelinks a WHERE a.articlelinksPK.sourceid = :sourceid")})
public class Articlelinks implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticlelinksPK articlelinksPK;
    @Basic(optional = false)
    @Column(name = "articleurl", nullable = false, length = 200)
    private String articleurl;
    @JoinColumn(name = "sourceid", referencedColumnName = "sourceid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Source source;
    @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Article article;

    public Articlelinks() {
    }

    public Articlelinks(ArticlelinksPK articlelinksPK) {
        this.articlelinksPK = articlelinksPK;
    }

    public Articlelinks(ArticlelinksPK articlelinksPK, String articleurl) {
        this.articlelinksPK = articlelinksPK;
        this.articleurl = articleurl;
    }

    public Articlelinks(int articleid, int sourceid) {
        this.articlelinksPK = new ArticlelinksPK(articleid, sourceid);
    }

    public ArticlelinksPK getArticlelinksPK() {
        return articlelinksPK;
    }

    public void setArticlelinksPK(ArticlelinksPK articlelinksPK) {
        this.articlelinksPK = articlelinksPK;
    }

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articlelinksPK != null ? articlelinksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articlelinks)) {
            return false;
        }
        Articlelinks other = (Articlelinks) object;
        if ((this.articlelinksPK == null && other.articlelinksPK != null) || (this.articlelinksPK != null && !this.articlelinksPK.equals(other.articlelinksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Articlelinks[ articlelinksPK=" + articlelinksPK + " ]";
    }
    
}
