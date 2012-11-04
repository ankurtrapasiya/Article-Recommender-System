/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Favourites;
import com.surpriseme.helper.FavouritesPK;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface FavouritesDAO extends GenericDAO<Favourites, FavouritesPK> {
    boolean addArticleToFavourites(Favourites fav) throws SQLException;

    boolean checkIfExistInFavourites(Integer userid, Integer articleId) throws SQLException;

    boolean checkIfExistInReadLater(Integer userid, Integer articleId) throws SQLException;
    
    boolean checkIfEntryExists(Integer userid, Integer articleId) throws SQLException;
}
