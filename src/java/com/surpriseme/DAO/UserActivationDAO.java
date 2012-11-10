/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.UserActivation;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface UserActivationDAO extends GenericDAO<UserActivation, Integer> {

    public boolean verifyToken(String token) throws SQLException;

    boolean activateUser(String token) throws SQLException;
}
