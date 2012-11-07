/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.ArticleTag;
import com.surpriseme.entities.Tag;
import com.surpriseme.helper.ArticleTagPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface ArticleTagDAO extends GenericDAO<ArticleTag, ArticleTagPK> {
    
    List<Tag> getAllTagsOfArticle(Integer articleId) throws SQLException;
}
