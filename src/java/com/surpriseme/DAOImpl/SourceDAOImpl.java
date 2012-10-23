/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.SourceDAO;
import com.surpriseme.entities.Source;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class SourceDAOImpl  implements SourceDAO{

  
    @Override
    public Source findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Source> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<Source> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet saveOrUpdate(Source entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet saveOrUpdateAll(List<Source> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

   
    
}
