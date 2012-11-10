/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Article;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface ArticleDAO extends GenericDAO<Article, Integer> {

    boolean updateViews(Integer articleId) throws SQLException;

    Integer vote(Integer articleId, boolean up) throws SQLException;

    boolean checkIfArticleExist(String guid) throws SQLException;
    
    List<Article> getRelevantArticles(String keyword) throws SQLException;
}
