/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleInterestDAO;
import com.surpriseme.entities.ArticleInterest;
import com.surpriseme.helper.ArticleInterestPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public class ArticleInterestDAOImpl implements ArticleInterestDAO{

    @Override
    public ArticleInterestPK saveOrUpdate(ArticleInterest entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<ArticleInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArticleInterest findById(ArticleInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ArticleInterest> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(ArticleInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<ArticleInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
