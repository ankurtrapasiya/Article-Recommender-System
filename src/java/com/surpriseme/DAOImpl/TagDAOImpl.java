/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.TagDAO;
import com.surpriseme.entities.Tag;

/**
 *
 * @author ankur
 */
public class TagDAOImpl extends GenericDAOImpl<Tag, Integer> implements TagDAO {

    @Override
    public boolean addTagToArticle(Integer tagId, Integer ArticleId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
