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