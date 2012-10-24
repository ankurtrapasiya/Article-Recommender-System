/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.InterestDAO;
import com.surpriseme.entities.Interest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class InterestDAOImpl implements InterestDAO {

    @Override
    public boolean saveOrUpdate(Interest entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<Interest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Interest findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Interest> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<Interest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
   
}
