package com.surpriseme.entities;

public class Source {

    private Integer sourceid;
    private String name;
    private String url;
    private String feedurl;
    private String icon;
    private Boolean isactive;

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Source{" + "sourceid=" + sourceid + ", name=" + name + ", url=" + url + ", feedurl=" + feedurl + ", icon=" + icon + ", isactive=" + isactive + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.sourceid != null ? this.sourceid.hashCode() : 0);
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.url != null ? this.url.hashCode() : 0);
        hash = 83 * hash + (this.feedurl != null ? this.feedurl.hashCode() : 0);
        hash = 83 * hash + (this.icon != null ? this.icon.hashCode() : 0);
        hash = 83 * hash + (this.isactive != null ? this.isactive.hashCode() : 0);
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
        final Source other = (Source) obj;
        if (this.sourceid != other.sourceid && (this.sourceid == null || !this.sourceid.equals(other.sourceid))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.url == null) ? (other.url != null) : !this.url.equals(other.url)) {
            return false;
        }
        if ((this.feedurl == null) ? (other.feedurl != null) : !this.feedurl.equals(other.feedurl)) {
            return false;
        }
        if ((this.icon == null) ? (other.icon != null) : !this.icon.equals(other.icon)) {
            return false;
        }
        if (this.isactive != other.isactive && (this.isactive == null || !this.isactive.equals(other.isactive))) {
            return false;
        }
        return true;
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

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}