/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.helper.ArticleLinksPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface ArticleLinksDAO extends GenericDAO<ArticleLinks, ArticleLinksPK> {

    List<ArticleLinks> getSourcesOfArticle(Integer articleId) throws SQLException;
}
