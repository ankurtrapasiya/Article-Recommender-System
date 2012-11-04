/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.helper;

import java.io.Serializable;

/**
 *
 * @author ankur
 */
public class BlockedUserPK implements Serializable{

    private Integer userid;
    private Integer blockerId;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(Integer blockerId) {
        this.blockerId = blockerId;
    }
}
