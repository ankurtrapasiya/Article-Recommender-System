package com.surpriseme.entities;

public class ArticleLinks {

    private Integer articleid;
    private String articleurl;
    private Integer sourceid;

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 97 * hash + (this.articleurl != null ? this.articleurl.hashCode() : 0);
        hash = 97 * hash + (this.sourceid != null ? this.sourceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticleLinks other = (ArticleLinks) obj;
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if ((this.articleurl == null) ? (other.articleurl != null) : !this.articleurl.equals(other.articleurl)) {
            return false;
        }
        if (this.sourceid != other.sourceid && (this.sourceid == null || !this.sourceid.equals(other.sourceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArticleLinks{" + "articleid=" + articleid + ", articleurl=" + articleurl + ", sourceid=" + sourceid + '}';
    }
}