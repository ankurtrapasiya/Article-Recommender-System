/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.TagInterestDAO;
import com.surpriseme.entities.TagInterest;
import com.surpriseme.helper.TagInterestPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class TagInterestDAOImpl implements TagInterestDAO{

    @Override
    public TagInterestPK saveOrUpdate(TagInterest entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<TagInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TagInterest findById(TagInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TagInterest> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(TagInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<TagInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
