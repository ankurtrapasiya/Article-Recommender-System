/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserActivationDAO;
import com.surpriseme.entities.UserActivation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class UserActivationDAOImpl implements UserActivationDAO{

    @Override
    public Integer saveOrUpdate(UserActivation entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserActivation> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserActivation findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserActivation> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserActivation> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
