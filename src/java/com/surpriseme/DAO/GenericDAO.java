/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ankur
 */


//First parameter is type of the entity and second parameter specifies the type of the primary key
public interface GenericDAO<T,ID extends Serializable> {
    
    boolean add(T entity);

    boolean saveOrUpdate(T entity);
    
    boolean saveOrUpdateAll(List<T> entities);
        
    T findById(ID key);
    
    List<T> getAll();
    
    boolean delete(ID key);
    
    boolean deleteAll(List<T> entities);
    
}
