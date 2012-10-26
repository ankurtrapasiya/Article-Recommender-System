/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface DAOMisc {
    
    Integer getLastInsertedId() throws SQLException;
    
}
