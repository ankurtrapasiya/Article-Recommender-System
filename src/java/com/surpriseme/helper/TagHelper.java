/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.helper;

/**
 *
 * @author ankur
 */
public class TagHelper {

    private String tagname;
    private Integer counts;

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public TagHelper(String tagname, Integer counts) {
        this.tagname = tagname;
        this.counts = counts;
    }
}
