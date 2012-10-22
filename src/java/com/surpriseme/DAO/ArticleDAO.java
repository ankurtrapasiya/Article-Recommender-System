/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Article;

/**
 *
 * @author ankur
 */
public interface ArticleDAO extends GenericDAO<Article, Integer> {
    
    boolean updateViews(Integer articleId);
    
    boolean vote(Integer articleId,boolean up);
}
