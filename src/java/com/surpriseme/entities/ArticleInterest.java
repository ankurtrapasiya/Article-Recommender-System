package com.surpriseme.entities;

public class ArticleInterest {

    private Integer articleid;
    private Integer interestid;

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getInterestid() {
        return interestid;
    }

    public void setInterestid(Integer interestid) {
        this.interestid = interestid;
    }

    @Override
    public String toString() {
        return "ArticleInterest{" + "articleid=" + articleid + ", interestid=" + interestid + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.articleid != null ? this.articleid.hashCode() : 0);
        hash = 43 * hash + (this.interestid != null ? this.interestid.hashCode() : 0);
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
        final ArticleInterest other = (ArticleInterest) obj;
        if (this.articleid != other.articleid && (this.articleid == null || !this.articleid.equals(other.articleid))) {
            return false;
        }
        if (this.interestid != other.interestid && (this.interestid == null || !this.interestid.equals(other.interestid))) {
            return false;
        }
        return true;
    }
}
