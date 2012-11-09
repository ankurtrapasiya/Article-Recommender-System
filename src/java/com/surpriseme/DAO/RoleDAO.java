/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Role;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface RoleDAO extends GenericDAO<Role, String> {

    boolean assignRole(String rolename, Integer userid) throws SQLException;
}
