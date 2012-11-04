/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Article;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface ArticleDAO extends GenericDAO<Article, Integer> {

    boolean updateViews(Integer articleId) throws SQLException;

    Integer vote(Integer articleId, boolean up) throws SQLException;

    boolean checkIfArticleExist(String guid) throws SQLException;
}
