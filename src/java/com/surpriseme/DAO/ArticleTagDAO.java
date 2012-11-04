/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.ArticleTag;
import com.surpriseme.helper.ArticleTagPK;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface ArticleTagDAO extends GenericDAO<ArticleTag, ArticleTagPK> {

    boolean addTagToArticle(Integer tagId, Integer ArticleId) throws SQLException;

    boolean removeTagFromArticle(Integer tagId, Integer ArticleId) throws SQLException;
}
