/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

/**
 *
 * @author ankur
 */
public enum Duration {

    WEEK(7),
    MONTH(30),
    YEAR(365);
        
    private int duration;

    private Duration(int duration) {
        this.duration = duration;
    }
            
    public int getDuration(){
        return this.duration;                
    }
}
