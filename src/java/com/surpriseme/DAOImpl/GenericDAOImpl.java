/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.GenericDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ankur
 */
public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("surprisemePU");
    EntityManager em = emf.createEntityManager();

    @Override
    public boolean add(T entity) throws Exception{
        boolean retval = false;
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw e;
        }


        return retval;
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean saveOrUpdateAll(List<T> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T findById(ID key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(ID key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<T> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
