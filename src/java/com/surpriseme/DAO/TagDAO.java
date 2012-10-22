/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Tag;

/**
 *
 * @author ankur
 */
public interface TagDAO extends GenericDAO<Tag, Integer>{
    
    boolean addTagToArticle(Integer tagId,Integer ArticleId);
}
