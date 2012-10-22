/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.entities.Article;

/**
 *
 * @author ankur
 */
public class ArticleDAOImpl extends GenericDAOImpl<Article,Integer> implements ArticleDAO{

    @Override
    public boolean updateViews(Integer articleId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean vote(Integer articleId, boolean up) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
