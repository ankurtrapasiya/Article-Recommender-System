/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.RoleDAO;
import com.surpriseme.entities.Role;

/**
 *
 * @author ankur
 */
public class RoleDAOImpl  extends GenericDAOImpl<Role, String> implements RoleDAO{

    @Override
    public boolean assignRole(String rolename, Integer userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
