/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */


//First parameter is type of the entity and second parameter specifies the type of the primary key
public interface GenericDAO<T,ID extends Serializable> {
        
    Integer saveOrUpdate(T entity) throws SQLException;
    
    void saveOrUpdateAll(List<T> entities) throws SQLException;
        
    T findById(ID key) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    boolean delete(ID key) throws SQLException;
    
    boolean deleteAll(List<T> entities) throws SQLException;
    
}
