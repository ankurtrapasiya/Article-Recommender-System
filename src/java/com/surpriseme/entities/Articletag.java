/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "articletag", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articletag.findAll", query = "SELECT a FROM Articletag a"),
    @NamedQuery(name = "Articletag.findByArticleid", query = "SELECT a FROM Articletag a WHERE a.articletagPK.articleid = :articleid"),
    @NamedQuery(name = "Articletag.findByTagid", query = "SELECT a FROM Articletag a WHERE a.articletagPK.tagid = :tagid"),
    @NamedQuery(name = "Articletag.findByTimestamp", query = "SELECT a FROM Articletag a WHERE a.articletagPK.timestamp = :timestamp")})
public class Articletag implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticletagPK articletagPK;
    @JoinColumn(name = "tagid", referencedColumnName = "tagid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tag tag;
    @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Article article;

    public Articletag() {
    }

    public Articletag(ArticletagPK articletagPK) {
        this.articletagPK = articletagPK;
    }

    public Articletag(int articleid, int tagid, Date timestamp) {
        this.articletagPK = new ArticletagPK(articleid, tagid, timestamp);
    }

    public ArticletagPK getArticletagPK() {
        return articletagPK;
    }

    public void setArticletagPK(ArticletagPK articletagPK) {
        this.articletagPK = articletagPK;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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
        hash += (articletagPK != null ? articletagPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articletag)) {
            return false;
        }
        Articletag other = (Articletag) object;
        if ((this.articletagPK == null && other.articletagPK != null) || (this.articletagPK != null && !this.articletagPK.equals(other.articletagPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Articletag[ articletagPK=" + articletagPK + " ]";
    }
    
}
