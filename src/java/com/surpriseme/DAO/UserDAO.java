/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    boolean blockUser(BlockedUsers entity) throws SQLException;
    
    boolean unblockUser(BlockedUsers userid) throws SQLException;      
    
    List<User> searchUser(String firstName,String lastName, String email) throws SQLException;
    
    boolean addToCircle(Integer friendId,Integer userId) throws SQLException;
    
    boolean removeFromCircle(Integer friendId,Integer userId) throws SQLException;
        
}
