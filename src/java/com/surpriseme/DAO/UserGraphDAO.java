/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.User;
import com.surpriseme.entities.UserGraph;
import com.surpriseme.helper.UserGraphPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserGraphDAO extends GenericDAO<UserGraph, UserGraphPK> {

    boolean addToCircle(Integer friendId, Integer userId) throws SQLException;

    boolean removeFromCircle(Integer friendId, Integer userId) throws SQLException;

    List<User> updateUserGraph() throws SQLException;

    List<User> getAllFriends(Integer userid) throws SQLException;
    
    Integer getSuggestionCount(Integer userid) throws SQLException;
}
