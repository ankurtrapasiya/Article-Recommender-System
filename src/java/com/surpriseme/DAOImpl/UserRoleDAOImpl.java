/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserRoleDAO;
import com.surpriseme.entities.UserRole;
import com.surpriseme.helper.UserRolePK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class UserRoleDAOImpl implements UserRoleDAO{

    @Override
    public UserRolePK saveOrUpdate(UserRole entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserRole> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserRole findById(UserRolePK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserRole> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UserRolePK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserRole> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
