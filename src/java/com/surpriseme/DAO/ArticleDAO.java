/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Article;
import com.surpriseme.entities.Favourites;
import com.surpriseme.utils.Category;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface ArticleDAO extends GenericDAO<Article, Integer> {

    boolean updateViews(Integer articleId) throws SQLException;

    Integer vote(Integer articleId, boolean up) throws SQLException;

    HashMap<Category, List<Article>> suggestArticle(int userid, int interestid) throws SQLException;

    boolean suggestArticle(int userid, int friendid, int articleid) throws SQLException;

    boolean checkIfArticleExist(String guid) throws SQLException;

    boolean addSourceToArticle(Integer articleid, String articleurl, Integer sourceid) throws SQLException;

    boolean addArticleToFavourites(Favourites fav) throws SQLException;

    boolean checkIfExistInFavourites(Integer userid, Integer articleId) throws SQLException;

    boolean checkIfExistInReadLater(Integer userid, Integer articleId) throws SQLException;
    
    boolean checkIfEntryExists(Integer userid, Integer articleId) throws SQLException;
    
}
