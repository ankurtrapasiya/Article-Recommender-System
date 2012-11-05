/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

/**
 *
 * @author peace
 */
public class Image {

    private String name;
    private String column;
    private String table;
    private String path_url;
    private String file_url;
    private String P_key;
    private String P_value;
    private String defaultImage;

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    /**
     * Get the value of P_value
     *
     * @return the value of P_value
     */
    public String getP_value() {
        return P_value;
    }

    /**
     * Set the value of P_value
     *
     * @param P_value new value of P_value
     */
    public void setP_value(String P_value) {
        this.P_value = P_value;
    }

    /**
     * Get the value of P_key
     *
     * @return the value of P_key
     */
    public String getP_key() {
        return P_key;
    }

    /**
     * Set the value of P_key
     *
     * @param P_key new value of P_key
     */
    public void setP_key(String P_key) {
        this.P_key = P_key;
    }

    /**
     * Get the value of file_url
     *
     * @return the value of file_url
     */
    public String getFile_url() {
        return file_url;
    }

    /**
     * Set the value of file_url
     *
     * @param file_url new value of file_url
     */
    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    /**
     * Get the value of path_url
     *
     * @return the value of path_url
     */
    public String getPath_url() {
        return path_url;
    }

    /**
     * Set the value of path_url
     *
     * @param path_url new value of path_url
     */
    public void setPath_url(String path_url) {
        this.path_url = path_url;
    }

    /**
     * Get the value of column
     *
     * @return the value of column
     */
    public String getColumn() {
        return column;
    }

    /**
     * Set the value of column
     *
     * @param column new value of column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /* Get the value of table
     *
     * @return the value of table
     */
    public String getTable() {
        return table;
    }

    /**
     * Set the value of table
     *
     * @param table new value of table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /* Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
}