/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

/**
 *
 * @author ankur
 */
public class UtilityMethods {

    public static java.sql.Date getSqlDate(java.util.Date date) {
        java.sql.Date sdate = new java.sql.Date(date.getTime());
        return sdate;
    }
}
