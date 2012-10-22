/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Role;

/**
 *
 * @author ankur
 */
public interface RoleDAO extends GenericDAO<Role, String> {

    boolean assignRole(String rolename, Integer userid);
}
