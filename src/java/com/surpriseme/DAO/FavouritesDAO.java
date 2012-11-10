/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Favourites;
import com.surpriseme.helper.FavouritesPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface FavouritesDAO extends GenericDAO<Favourites, FavouritesPK> {
    
    List<Favourites> getAllFavourites(Integer userId) throws SQLException;
    
}
